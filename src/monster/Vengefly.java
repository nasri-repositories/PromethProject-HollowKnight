package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import item.GoldenSword;
import object.MoveableEnemy;

public class Vengefly extends MoveableEnemy {
	
	public Vengefly(double x, double y) {
		super(x, y, 130, 80);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Vengefly.png").toString(), 130, 80, false, true)));
		artList.add("normal");
		friction = 0.05;
		speed = 5;
		maxHp = 40;
		attackDamage = 20;
	}
	
	public void setMovement() {
		switch(cerrentStage) {
		case "idle":
			dx -= (dx)*friction;
			dy -= (dy)*friction;
			if (Math.pow((Main.hero.getX()+Main.hero.getSize()[0]/2) - (x+size[0]/2), 2) + 
					Math.pow((Main.hero.getY()+Main.hero.getSize()[1]/2) - (y+size[1]/2), 2)
					< 200000) {
				changeArt("normal");
			}
			break;
		case "normal":
			dx += ((turnLeft ? -speed : speed) - dx)*friction;
			dy += ((Main.hero.getY()+Main.hero.getSize()[1]/2 < y+size[1]/2 ? -speed : speed) - dy)*friction;
			turn(Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2);
			break;
		}
	}

	protected void reset() {
		cerrentStage = "idle";
		super.reset();
	}
	
	public void die() {
		Main.inventory.addItem(new GoldenSword());
		super.die();
	}
}