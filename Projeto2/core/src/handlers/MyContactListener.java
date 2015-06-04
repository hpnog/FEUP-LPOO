package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.SingletonVandC;

public class MyContactListener implements ContactListener {

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
				fa.setUserData("caughtKey");	
			else if (fa.getUserData() == "robot" && fb.getUserData() == "key")
				fb.setUserData("caughtKey");
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "diamond" && fb.getUserData() == "robot")
				fa.setUserData("caughtDiamond");	
			else if (fa.getUserData() == "robot" && fb.getUserData() == "diamond")
				fb.setUserData("caughtDiamond");
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "Danger" && fb.getUserData() == "robot")
				singleton.loseLife++;
			else if (fa.getUserData() == "robot" && fb.getUserData() == "Danger")
				singleton.loseLife++;
	}
	
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
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
