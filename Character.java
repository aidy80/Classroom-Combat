import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Character{
    int hitBoxDamage = 1;
	int x=0;
	int y=550;
    int width = 100;
    int standingheight;
    int crouchingheight;
    int height = standingheight;
	int health=100;
	int xvelocity;
	int yvelocity;
	int movespeed=6;
    int jumptimer = 0;
	boolean attacking = false;
	boolean cooldown=false;
	int attacktimer=0;
	String facing = "right";
	boolean left = false;
	boolean right = false;
	boolean down = false;
	boolean up = false;
    boolean jumping = false;
	String combo = "";
    boolean kicking = false;
	boolean firing = false;
    boolean crouching = false;
    boolean walking = false;
    boolean blocking = false;
    boolean punching = false;
    boolean flinching = false;
	int combotimer = 5;
	Game game;
	int playerNumber;
    Health healthbar;
    Rectangle hitbox = null;
    Rectangle hurtbox;
    boolean canDamage=true;


    //animation
    SpriteSheet spriteSheet = null;
    BufferedImage s;
    BufferedImage currentImage;
	int stillFrame = 0;
	int stillFrameDelay = 0;
    BufferedImage[] still = new BufferedImage[4];

    int jumpFrame = 0;
    int jumpFrameDelay = 0;
    BufferedImage[] jump = new BufferedImage[7];

    int fireFrame = 0;
    int fireFrameDelay = 0;
    BufferedImage[] fire = new BufferedImage[5];

    int crouchFrame = 0;
    int crouchFrameDelay = 0;
    BufferedImage[] crouch = new BufferedImage[2];

    int kickFrame = 0;
    int kickFrameDelay = 0;
    BufferedImage[] kick = new BufferedImage[3];

    int walkFrame = 0;
    int walkFrameDelay = 0;
    BufferedImage[] walk = new BufferedImage[5];

    int punchFrame = 0;
    int punchFrameDelay = 0;
    BufferedImage[] punch = new BufferedImage[3];

    int flinchFrame = 0;
    int flinchFrameDelay = 0;
    BufferedImage[] flinch = new BufferedImage[4];

    int groundAttackFrame = 0;
    int groundAttackFrameDelay = 0;
    BufferedImage[] groundAttack = new BufferedImage[4];

    int airAttackFrame = 0;
    int airAttackFrameDelay = 0;
    BufferedImage[] airAttack = new BufferedImage[3];

    BufferedImage[] block = new BufferedImage[2];

	public Character(Game game, int playerNumber, String spritePage){
        this.game = game;
        this.playerNumber = playerNumber;
        if (playerNumber ==2) {
            x=1100;
        }

        BufferedImageLoader loader = new BufferedImageLoader();
        try {

            s = loader.loadImage(spritePage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        spriteSheet = new SpriteSheet(s);
        if(playerNumber == 1) {
            facing = "right";
        }else {
            facing = "left";
        }
        init();
    }

    public void addHealth(Health healthbar){
        this.healthbar=healthbar;
    }

    public void init() {
        if(playerNumber == 1) {
            still[0] = spriteSheet.grabImage(6, 18, 43, 81);
            still[1] = spriteSheet.grabImage(55, 19, 43, 80);
            still[2] = spriteSheet.grabImage(105, 18, 43, 81);
            still[3] = spriteSheet.grabImage(154, 17, 43, 82);

            jump[0] = spriteSheet.grabImage(452, 24, 43, 75);
            jump[1] = spriteSheet.grabImage(503, 9, 33, 90);
            jump[2] = spriteSheet.grabImage(545, 17, 29, 78);
            jump[3] = spriteSheet.grabImage(582, 19, 31, 67);
            jump[4] = spriteSheet.grabImage(619, 17, 29, 78);
            jump[5] = spriteSheet.grabImage(656, 9, 33, 90);
            jump[6] = spriteSheet.grabImage(696, 24, 43, 75);

            fire[0] = spriteSheet.grabImage(4, 636, 51, 82);
            fire[1] = spriteSheet.grabImage(60, 642, 66, 76);
            fire[2] = spriteSheet.grabImage(131, 643, 66, 75);
            fire[3] = spriteSheet.grabImage(202, 648, 91, 70);
            fire[4] = spriteSheet.grabImage(299, 648, 70, 70);

            crouch[0] = spriteSheet.grabImage(1113, 24, 43, 75);
            crouch[1] = spriteSheet.grabImage(1160, 44, 43, 55);

            kick[0] = spriteSheet.grabImage(6, 261, 49, 85);
            kick[1] = spriteSheet.grabImage(62, 259, 67, 87);
            kick[2] = spriteSheet.grabImage(135, 261, 49, 85);


            walk[0] = spriteSheet.grabImage(205, 19, 43, 80);
            walk[4] = spriteSheet.grabImage(401, 19, 43, 80);
            walk[1] = spriteSheet.grabImage(252, 19, 43, 80);
            walk[2] = spriteSheet.grabImage(301, 18, 43, 81);
            walk[3] = spriteSheet.grabImage(351, 19, 43, 80);

            groundAttack[0] = spriteSheet.grabImage(180,419,47,55);
            groundAttack[1] = spriteSheet.grabImage(231,417,63,57);
            groundAttack[2] = spriteSheet.grabImage(298,419,45,55);
            groundAttack[3] = spriteSheet.grabImage(348,419,45,55);

            airAttack[0] = spriteSheet.grabImage(467,551,38,64);
            airAttack[1] = spriteSheet.grabImage(510,554,63,55);
            airAttack[2] = spriteSheet.grabImage(576,551,38,64);

            block[0] = spriteSheet.grabImage(1210,16,44,83);
            block[1] = spriteSheet.grabImage(1260,37,44,62);

            punch[0] = spriteSheet.grabImage(3,113,44,83);
            punch[1] = spriteSheet.grabImage(51,133,58,83);
            punch[2] = spriteSheet.grabImage(116,133,44,83);

            flinch[0] = spriteSheet.grabImage(216,757,49,79);
            flinch[1] = spriteSheet.grabImage(271,757,53,79);
            flinch[2] = spriteSheet.grabImage(328,752,58,84);
            flinch[3] = spriteSheet.grabImage(390,754,44,82);
        }

        if (playerNumber == 2) {
            still[0] = spriteSheet.grabImage(1549, 18, 43, 81);
            still[1] = spriteSheet.grabImage(1500, 19, 43, 80);
            still[2] = spriteSheet.grabImage(1450, 18, 43, 81);
            still[3] = spriteSheet.grabImage(1401, 17, 43, 82);

            jump[0] = spriteSheet.grabImage(1103, 24, 43, 75);
            jump[1] = spriteSheet.grabImage(1062, 9, 33, 90);
            jump[2] = spriteSheet.grabImage(1024, 17, 29, 78);
            jump[3] = spriteSheet.grabImage(985, 19, 31, 67);
            jump[4] = spriteSheet.grabImage(950, 17, 29, 78);
            jump[5] = spriteSheet.grabImage(909, 9, 33, 90);
            jump[6] = spriteSheet.grabImage(859, 24, 43, 75);

            fire[0] = spriteSheet.grabImage(931, 532, 51, 82);
            fire[1] = spriteSheet.grabImage(860, 538, 66, 76);
            fire[2] = spriteSheet.grabImage(789, 541, 66, 73);
            fire[3] = spriteSheet.grabImage(714, 544, 70, 70);


            crouch[0] = spriteSheet.grabImage(442, 24, 43, 75);
            crouch[1] = spriteSheet.grabImage(395, 43, 43, 56);

            kick[0] = spriteSheet.grabImage(1543, 261, 49, 85);
            kick[1] = spriteSheet.grabImage(1469, 261, 67, 85);
            kick[2] = spriteSheet.grabImage(1414, 261, 49, 85);

            walk[0] = spriteSheet.grabImage(1350, 24, 43, 75);
            walk[1] = spriteSheet.grabImage(1303, 19, 43, 80);
            walk[2] = spriteSheet.grabImage(1254, 17, 43, 82);
            walk[3] = spriteSheet.grabImage(1204, 19, 43, 80);
            walk[4] = spriteSheet.grabImage(1154, 19, 43, 80);

            groundAttack[0] = spriteSheet.grabImage(1370,416,48,60);
            groundAttack[1] = spriteSheet.grabImage(1305,418,61,56);
            groundAttack[2] = spriteSheet.grabImage(1255,419,45,55);
            groundAttack[3] = spriteSheet.grabImage(1205,419,45,55);

            airAttack[0] = spriteSheet.grabImage(1098,551,37,63);
            airAttack[1] = spriteSheet.grabImage(1030,557,63,51);
            airAttack[2] = spriteSheet.grabImage(989,551,37,63);

            block[0] = spriteSheet.grabImage(344,16,43,83);
            block[1] = spriteSheet.grabImage(295,38,43,61);

            punch[0] = spriteSheet.grabImage(3,113,44,83);
            punch[1] = spriteSheet.grabImage(51,133,58,83);
            punch[2] = spriteSheet.grabImage(116,133,44,83);

        }
    }

    public void tick() {
        if(hitbox!=null && canDamage) {
            collision();
        }

        //setHealth();
        if (jumping) {
            crouching = false;
            jumptimer += 1;
            jump();
        }
        if (attacking || cooldown) {
            attacktimer++;
            if(attacktimer == 30) {
                kicking = false;
            }
            if (attacktimer == 50) {
                attacking = false;
                firing = false;
                cooldown = true;
            }

            if (attacktimer == 60) {
                cooldown = false;
                canDamage=true;
                attacktimer = 0;
            }
        }

        if (!left && !right && !down && !up) {
            combotimer -= 1;
            if (combotimer == 0) {
                combotimer = 5;
                combo = "";
            }
        } else {
            combotimer = 5;
        }
        if (crouching) {
            height = crouchingheight;
        } else {
            height = standingheight;
        }

        if (!jumping) {
            jumpFrame = 0;
            jumpFrameDelay = 0;
        }

        if (!walking) {
            walkFrame = 0;
            walkFrameDelay = 0;
        }

        if (!crouching) {
            crouchFrame = 0;
            crouchFrameDelay = 0;
        }

        if (!kicking) {
            kickFrame = 0;
            kickFrameDelay = 0;
        }

        if (!firing) {
            fireFrame = 0;
            fireFrameDelay = 0;
        }


        if (jumping || crouching || kicking || firing || walking) {
            stillFrame = 0;
            stillFrameDelay = 0;
        }

        if (!jumping && !crouching && !attacking && !firing && !walking) {
            currentImage = still[stillFrame];
            stillFrameDelay++;
            if (stillFrameDelay >= 10) {
                if (stillFrame < still.length - 1) {
                    stillFrame++;
                } else {
                    stillFrame = 0;
                }
                stillFrameDelay = 0;
            }
        }

        if (jumping) {
            currentImage = jump[jumpFrame];
            jumpFrameDelay++;
            if (jumpFrameDelay >= 15) {
                if (jumpFrame < jump.length - 1) {
                    jumpFrame++;
                } else {
                    jumpFrame = 0;
                }
                jumpFrameDelay = 0;
            }
        }

        if (firing) {
            currentImage = fire[fireFrame];
            fireFrameDelay++;
            if (fireFrameDelay >= 10) {
                if (fireFrame < fire.length - 1) {
                    fireFrame++;
                } else {
                    fireFrame = 0;
                }
                fireFrameDelay = 0;
            }
        }

        if (flinching) {
            currentImage = flinch[flinchFrame];
            flinchFrameDelay++;
            if (flinchFrameDelay >= 10) {
                if (flinchFrame < flinch.length - 1) {
                    flinchFrame++;
                } else {
                    flinchFrame = 0;
                }
                flinchFrameDelay = 0;
            }
        }

        if(crouching) {
            currentImage = crouch[crouchFrame];
            if(crouchFrame!= 1) {
                crouchFrameDelay++;
            }
            if (crouchFrameDelay>=10){
                crouchFrame = 1;
            }
        }

        if (kicking) {
            if(playerNumber == 1) {
                hitbox = new Rectangle(x+100,y+50,100,100);
            }
            else{
                hitbox = new Rectangle(x-100,y+50,100,100);
            }
            currentImage = kick[kickFrame];
            kickFrameDelay++;
            if (kickFrameDelay >= 10) {
                if (kickFrame < kick.length - 1) {
                    kickFrame++;
                } else {
                    kickFrame = 0;
                }
                kickFrameDelay = 0;
            }
        }

        if (punching) {
            if(playerNumber == 1) {
                hitbox = new Rectangle(x+100,y+50,100,100);
            }
            else{
                hitbox = new Rectangle(x-100,y+50,100,100);
            }
            currentImage = punch[punchFrame];
            punchFrameDelay++;
            if (punchFrameDelay >= 10) {
                if (punchFrame < punch.length - 1) {
                    punchFrame++;
                } else {
                    punchFrame = 0;
                }
                punchFrameDelay = 0;
            }
        }

        if (walking && !crouching) {
            currentImage = walk[walkFrame];
            walkFrameDelay++;
            if (walkFrameDelay >= 10) {
                if (walkFrame < walk.length - 1) {
                    walkFrame++;
                } else {
                    walkFrame = 0;
                }
                walkFrameDelay = 0;
            }
        }

        if (attacking && crouching) {
            if(playerNumber == 1) {
                hitbox = new Rectangle(x+100,y+50,100,100);
            }
            else{
                hitbox = new Rectangle(x-100,y+50,100,100);
            }
            currentImage = groundAttack[groundAttackFrame];
            groundAttackFrameDelay++;
            if (groundAttackFrameDelay >= 10) {
                if (groundAttackFrame < groundAttack.length - 1) {
                    groundAttackFrame++;
                } else {
                    groundAttackFrame = 0;
                }
                groundAttackFrameDelay = 0;
            }
        }

        if (attacking && jumping) {
            if(playerNumber == 1) {
                hitbox = new Rectangle(x+100,y+50,100,100);
            }
            else{
                hitbox = new Rectangle(x-100,y+50,100,100);
            }
            currentImage = airAttack[airAttackFrame];
            airAttackFrameDelay++;
            if (airAttackFrameDelay >= 10) {
                if (airAttackFrame < airAttack.length - 1) {
                    airAttackFrame++;
                } else {
                    airAttackFrame = 0;
                }
                airAttackFrameDelay = 0;
            }
        }

        if (blocking) {
            if (crouching) {
                currentImage = block[1];
            }
            else {
                currentImage = block[0];
            }
        }
    }

    public int getHealth() {
        return health;
    }

    //public void setHealth() {
        //if(otherCharacter.getHitBox().intersects(hurtbox)) {
            //healthbar.damage(health, 10);
            //health = healthbar.getLife();
        //}
    //}

    public void setxpos(int newxpos){
		if(newxpos<0) {
            x=0;
        }
        else if(newxpos>1100) {
            x=1100;
        }
        else if(playerNumber==1&&x>=game.characters.get(1).x){
            x=game.characters.get(1).x-1;
        }
        else if(playerNumber==2&&x<=game.characters.get(0).x){
            x=game.characters.get(0).x+1;
        }
        else if(playerNumber==1 && game.characters.get(1).getHurtBox().intersects(getHurtBox())){

            if(x<game.characters.get(1).x){
                x-=movespeed;
                game.characters.get(1).x+=movespeed;
            }

        }
        else if(playerNumber==2&&game.characters.get(0).getHurtBox().intersects(getHurtBox())){
            if(x>=game.characters.get(0).x){
                x+=movespeed;
                game.characters.get(0).x-=movespeed;
            }

        }
        else {
            x = newxpos;
        }
    }

    public void setypos(int newypos){
        if(newypos<=550) {
            y = newypos;
        }
        else{
            y=550;
        }
	}

    public Rectangle getHurtBox(){
        if(crouching&&!blocking){
            return new Rectangle(x, y, 150,100);
        }
        else if(crouching && blocking){
            return new Rectangle(x, y, 150,150);
        }
        else{
            return new Rectangle(x, y, 150,200);
        }
    }

    public Rectangle getHitBox() {
        return hitbox;
    }

    public void collision() {
        if(playerNumber == 1 && !game.characters.get(1).blocking){
            if(game.characters.get(1).getHurtBox().intersects(getHitBox())) {
                game.characters.get(1).healthbar.damage(hitBoxDamage);
                hitbox=null;
                canDamage=false;
            }
            for(Projectile x : game.projectiles){
                if (game.characters.get(1).getHurtBox().intersects(x.getProjectileHitBox())){
                    game.characters.get(1).healthbar.damage(hitBoxDamage);
                    x.width=0;
                    x.height=0;
                }
            }
        }
        if(playerNumber == 2 && !game.characters.get(0).blocking) {
            if (game.characters.get(0).getHurtBox().intersects(getHitBox())) {
                game.characters.get(0).healthbar.damage(hitBoxDamage);
                hitbox = null;
                canDamage=false;
            }

            for(Projectile x : game.projectiles){
                if (game.characters.get(0).getHurtBox().intersects(x.getProjectileHitBox())){
                    game.characters.get(0).healthbar.damage(hitBoxDamage);
                    x.width=0;
                    x.height=0;
                }
            }
        }
    }
    public void move(){
        if(!crouching && !blocking) {
            setxpos(x + xvelocity);
        }
        setypos(y - yvelocity);
	}

    public void attack(){
		switch(combo){
			case "dr": kicking=true; hitbox = new Rectangle(x+100,y+50,200,200); break;
			case "dl": kicking=true; hitbox = new Rectangle(x+100,y+50,200,200); break;
			case "lr": new Projectile(x+200,y+200,"RyuBetter.png", playerNumber,game);
                firing=true;
                break;
			case "rl": new Projectile(x+200,y+200,"RyuBetter.png", playerNumber,game);
                firing=true;
                break;
		}

		System.out.println(combo);
		combo="";
		if(!attacking&&!cooldown) {
			attacking = true;
		}
	}



    public void jump(){
        if(!jumping) {
            jumping = true;
            jumptimer=0;
        }
        else{
            walking = false;
            if(jumptimer<10){
                yvelocity=20;
            }
            else if(jumptimer<20){
                yvelocity=10;
            }
            else if(jumptimer<30){
                yvelocity=5;
            }
            else if(jumptimer<40){
                yvelocity=1;
            }
            else if(jumptimer<50){
                yvelocity=-1;
            }
            else if(jumptimer<60){
                yvelocity=-5;
            }
            else if(jumptimer<70){
                yvelocity=-10;
            }
            else if(jumptimer<80){
                yvelocity=-20;
            }
            else if(jumptimer==80){
                yvelocity=0;
                jumping=false;
            }
        }

    }

    /**
    public void paint(Graphics2D g) {
        if(crouching){
            g.fillRect(x, y+(standingheight-crouchingheight), width,height);
        }
        else {
            g.fillRect(x, y, width, height);
        }

        if(attacking&&!kicking&&!firing&&!crouching){
            g.setColor(Color.BLUE);
            if(facing=="right"){
                g.fillRect(x + 100, y + (2*standingheight-3*crouchingheight)-5, 100, 50);
            }
            if(facing=="left") {
                g.fillRect(x - 100, y + (2*standingheight-3*crouchingheight)-5, 100, 50);
            }
            g.setColor(Color.RED);
        }
        if(attacking && !firing && (kicking || crouching)){
            g.setColor(Color.BLUE);
            if(facing == "right") {
                g.fillRect(x + 100, y + 150, 100, 50);
            }
            if(facing == "left") {
                g.fillRect(x - 100, y + 150, 100, 50);
            }
            g.setColor(Color.RED);
        }
    }
     */

    public void paint(Graphics2D g) {
        if(!crouching) {
            g.drawImage(currentImage, x, y, 200, 200, null);
        }
        else if (crouching && !blocking){
            g.drawImage(currentImage, x, y+100, 200, 100, null);
        }
        else {
            g.drawImage(currentImage, x, y+50, 200, 150, null);
        }
	}

    public void keyPressed(KeyEvent e) { // Movement controls
        if(playerNumber==2) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xvelocity = -movespeed;
                left = true;
                combo += "l";
                walking = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                xvelocity = movespeed;
                right = true;
                combo += "r";
                walking = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                down = true;
                combo += "d";
                crouching=true;


            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = true;
                combo += "u";
                if(!blocking) {
                    jump();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SLASH) {
                attack();
            }
            if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
                blocking = true;
            }
        }
        if(playerNumber==1){
            if (e.getKeyCode() == KeyEvent.VK_A) {
                xvelocity = -movespeed;
                left = true;
                combo += "l";
                walking = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                xvelocity = movespeed;
                right = true;
                combo += "r";
                walking = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = true;
                combo += "d";
                crouching=true;


            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = true;
                combo += "u";
                if(!blocking) {
                    jump();
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_G) {
                attack();
            }
            if (e.getKeyCode() == KeyEvent.VK_H) {
                blocking = true;
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        if(playerNumber==2) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!right) {
                    xvelocity = 0;
                    walking = false;
                } else {
                    xvelocity = movespeed;
                    facing = "right";
                }
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!left) {
                    xvelocity = 0;
                    walking = false;
                } else {
                    xvelocity = -movespeed;
                    facing = "left";
                }
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                down = false;
                crouching=false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
                blocking = false;
            }
        }
        if(playerNumber==1) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (!right) {
                    xvelocity = 0;
                    walking = false;
                } else {
                    xvelocity = movespeed;
                    facing = "right";
                }
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (!left) {
                    xvelocity = 0;
                    walking = false;
                } else {
                    xvelocity = -movespeed;
                    facing = "left";
                }
                right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                down = false;
                crouching=false;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_H) {
                blocking = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_G) {
                punching = true;
            }
        }
    }

}


