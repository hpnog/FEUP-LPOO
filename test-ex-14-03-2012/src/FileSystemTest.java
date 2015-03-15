import junit.framework.TestCase;
import java.util.Arrays;

public class FileSystemTest extends TestCase {

	public void testCreateFileSystem() {
		FileSystem fs = new FileSystem("FAT32");
		assertEquals("FAT32", fs.getType());		
	}

	public void testFileSystemRoot() {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();
		assertNull(root.getParent());
		assertSame(root,fs.getRoot());
	}

	public void testCreateNodes() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();	
		
		Node node1 = new Folder(root, "bin");
		assertSame(root, node1.getParent());
		assertEquals("bin", node1.getName());

		Node node2 = new File(root, "readme.txt");
		assertSame(root, node2.getParent());
		assertEquals("readme.txt", node2.getName());
	}
	
	public void testFolderContents() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Node node1 = new Folder(root, "bin");
		Node node2 = new File(root, "readme.txt");
		Node[] child = {node1, node2};
		assertTrue(Arrays.equals(child, root.getChild()));
	}
	
	public void testGetChildByName() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Node node1 = new Folder(root, "bin");
		assertSame(node1, root.getChildByName("bin"));
		assertNull(root.getChildByName("bin2"));
	}
	
	public void testDuplicateName() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Node node1 = new Folder(root, "bin");
		try {
			Node node2 = new File(root, "bin");
			fail("Devia ter lançado DuplicateNameException");
		}
		catch(DuplicateNameException e) {			
		}
	}

	public void testAutoNumber() throws DuplicateNameException {
		Node.resetNumbering(0);
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		assertEquals(1, root.getNumber());
		Node node1 = new Folder(root, "bin");
		assertEquals(2, node1.getNumber());
		
		FileSystem fs2 = new FileSystem("FAT128");
		assertEquals(3, fs2.getRoot().getNumber());
	}
/*
	public void testSize() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Folder folder = new Folder(root, "bin");
		File file1 = new File(folder, "readme.txt", 100);
		assertEquals(100, file1.getSize());
		File file2 = new File(root, "readme.txt", 200);
		assertEquals(200, file2.getSize());
		assertEquals(300, root.getSize());
	}
	
	public void testPathNameFormatter() throws DuplicateNameException {
		NameFormatter dos = new DOSFormatter();
		NameFormatter unix = new UnixFormatter();
		
		assertEquals('/', unix.getSeparator());
		assertEquals('\\', dos.getSeparator());

		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Folder etc = new Folder(root, "etc");
		File crontab = new File(etc, "crontab");

		fs.setNameFormatter(unix);		
		assertEquals("/etc/crontab", crontab.getPath());		

		fs.setNameFormatter(dos);
		assertEquals("\\etc\\crontab", crontab.getPath());	
	}

	public void testDeepClone() throws DuplicateNameException {
		FileSystem fs = new FileSystem("FAT32");
		Folder root = fs.getRoot();		
		Folder etc = new Folder(root, "etc");
		Folder cron = new Folder(etc, "cron");
		File crontab = new File(cron, "crontab");
		
		// clone permite copiar um nó e todos os seus descendentes
		Folder etc2 = etc.clone(root, "etc2"); // newParent, newName
		assertSame(root, etc2.getParent());
		assertEquals("etc2", etc2.getName());

		// verifica que copiou também o filho
		Folder cron2 = (Folder)etc2.getChildByName("cron");
		assertNotSame(cron, cron2); // pastas diferentes
		assertEquals(cron, cron2); // mas com o mesmo conteúdo (nome, filhos) 
		
		// ... e o neto
		File crontab2 = (File)cron2.getChildByName("crontab");
		assertNotSame(crontab, crontab2); // ficheiros diferentes
		assertEquals(crontab, crontab2);  // mas com o mesmo conteúdo (nome, tamanho) 
	}
	

	public void testMove() throws DuplicateNameException, CycleException {
		FileSystem fs = new FileSystem("FAT32");
		fs.setNameFormatter(new UnixFormatter());
		Folder root = fs.getRoot();		
		Folder etc = new Folder(root, "etc");
		Folder cron = new Folder(etc, "cron");				
		File crontab = new File(etc, "crontab");		
		assertEquals("/etc/crontab", crontab.getPath());
		
		// "move" permite alterar o pai e/ou o nome de um nó
		crontab.move(cron, "crontab2"); //newParent, newName
		assertEquals("/etc/cron/crontab2", crontab.getPath());
		
		// não se pode mover um nó para debaixo dele próprio ou de um descendente,
		// pois criaria um ciclo
		try {
			etc.move(cron, "etc");
			fail("Devia lançar CycleException");
		}
		catch(CycleException e) {			
		}

		// caso normal, só para verificar que recuperou bem da excepção anterior
		cron.move(root, "cron");
		assertEquals("/cron/crontab2", crontab.getPath());	
	}*/
}