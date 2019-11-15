package object;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveableCharacter extends MoveableObject implements Destroyable {
	
	protected double attackDamage, maxHp, hp;
	protected boolean turnLeft, inAir;
	protected List<String> artList = new ArrayList<String>();
	
	public MoveableCharacter(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public boolean hitCheck(double x, double y, double width, double height) {
		if (this.x <= x+width && 
			this.x + size[0] >= x &&
			this.y <= y+height && 
			this.y + size[1] >= y ) {
			return true;
		}
		return false;
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		if (knockbackX != 0) {
			dx = knockbackX;
		}
		if (knockbackY != 0) {
			dy = -knockbackY;
		}
		hp = damage > hp ? 0 : hp - damage;
		if (hp == 0) {
			die();
		}
	}
	
	protected void changeArt(String art) {
		getChildren().forEach((image)->{
			image.setVisible(false);
		});
		getChildren().get(artList.indexOf(art)).setVisible(true);
	}
	
	public void turn(boolean turnLeft) {
		this.turnLeft = turnLeft;
		setScaleX(turnLeft ? -1 : 1);
	}
	
	public void reset() {
		dx = 0;
		dy = 0;
		turn(false);
	}
	
	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}
}
