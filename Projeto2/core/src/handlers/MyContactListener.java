package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import static com.mygdx.entities.Robot.jumpReady;
import static com.mygdx.entities.Robot.exiting;

public class MyContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "robotFoot" && fb.getUserData() == "Floor" || fb.getUserData() == "robotFoot" && fa.getUserData() == "Floor")
				jumpReady++;
				
		if (fa.getUserData() != null && fb.getUserData() != null)
			if (fa.getUserData() == "robot" && fb.getUserData() == "doorCenter" || fb.getUserData() == "robot" && fa.getUserData() == "doorCenter" && exiting == 0)
				exiting = 1;
		
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
	}
	
	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if (fa.getUserData() != null)
			if (fa.getUserData() == "robotFoot")
				jumpReady--;
		
		if (fb.getUserData() != null)
			if (fb.getUserData() == "robotFoot")
				jumpReady--;
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
