package jumpyjay.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving myContact events.
 * The class that is interested in processing a myContact
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMyContactListener<code> method. When
 * the myContact event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MyContactEvent
 */
public class MyContactListener implements ContactListener {

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		SingletonVandC singleton = SingletonVandC.getSingleton();
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "robotFoot" && fb.getUserData() == "Floor" || fb.getUserData() == "robotFoot" && fa.getUserData() == "Floor")
				singleton.jumpReady++;
				
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "robot" && fb.getUserData() == "doorCenter" || fb.getUserData() == "robot" && fa.getUserData() == "doorCenter" && singleton.exiting == 0)
				singleton.exiting = 1;
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "key" && fb.getUserData() == "robot")
			{
				fa.setUserData("caughtKey");
				SoundHandler.playCaught();
			}
			else if (fa.getUserData() == "robot" && fb.getUserData() == "key")
			{
				fb.setUserData("caughtKey");
				SoundHandler.playCaught();
			}
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "diamond" && fb.getUserData() == "robot")
			{
				fa.setUserData("caughtDiamond");
				SoundHandler.playCaught();
			}
			else if (fa.getUserData() == "robot" && fb.getUserData() == "diamond")
			{
				fb.setUserData("caughtDiamond");
				SoundHandler.playCaught();
			}
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "Danger" && fb.getUserData() == "robot")
				singleton.loseLife++;
			else if (fa.getUserData() == "robot" && fb.getUserData() == "Danger")
				singleton.loseLife++;
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.gdx.physics.box2d.Contact)
	 */
	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		SingletonVandC singleton = SingletonVandC.getSingleton();
		
		if (fa.getUserData() != null)
			if (fa.getUserData() == "robotFoot")
				singleton.jumpReady--;
		
		if (fb.getUserData() != null)
			if (fb.getUserData() == "robotFoot")
				singleton.jumpReady--;
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "Danger" && fb.getUserData() == "robot")
				singleton.loseLife--;
			else if (fa.getUserData() == "robot" && fb.getUserData() == "Danger")
				singleton.loseLife--;
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
