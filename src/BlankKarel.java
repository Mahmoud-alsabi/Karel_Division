import stanford.karel.SuperKarel;
public class BlankKarel extends SuperKarel {
	private int totalMoves;
	public void run(){
		setBeepersInBag(1000);
		int x =getAxis(),y=getAxis();
		if(x==2&&y==2){
			putBeepers(1);
			karelRotate();
			turnRight();
			putBeepers(1);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if (x<2&&y<2) {
			System.out.println("Can't be divided !");
		}
		else if (x<3&&y>=7) {
			xLess3(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if (x < 3) {
			xLess3yLess7(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if (y < 3 && x < 7) {
			yLess3XLess7(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if (y<3) {
			yLess3(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if ((x%2!= 0)&&(y%2==0)) {
			xOddYEven(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if ((x%2==0)&&(y%2!=0)) {
			xEvenYOdd(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else if (x % 2 != 0) {
			bothOdd(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
		else {
			bothEven(x,y);
			System.out.println("Total moves: "+totalMoves);
			totalMoves =0;
		}
	}
	public void xLess3yLess7(int x,int y){
		if(y==3){
			turnLeft();
			karelDivide(1,x,1);
		}
		if (y == 6) {
			turnLeft();
			karelDivide(1,x,1);
			karelDivide(2,x,2);
		}
		if(y<6&&y!=3){
			turnLeft();
			karelDivide(1, x, 1);
			karelDivide(2, x, 1);
		}
	}
	public void yLess3XLess7(int x,int y){
		if(x==3){
			karelDivide(1,y,1);
		}
		if (x==6) {
			karelDivide(1,y,1);
			karelDivide(2,y,2);
		}
		if(x<6&&x!=3) {
			karelDivide(1, y, 1);
			karelDivide(2, y, 1);
		}
	}
	public void xLess3(int x,int y){
		if((y-3)%4==0&&x>1){
			karelDivide(y/4+1,x,3);
		}
		else if ((y-3)%4==0&&x==1) {
			turnLeft();
			karelDivide(y/4,x,1);
			karelDivide(y/4+1,x,2);
		} else {
			turnRight();
			fillings(y, x);
			turnLeft();
			karelDivide(y / 4, x, 3);
		}
	}
	public void yLess3(int x,int y){
		if((x-3)%4==0) {
			karelDivide(x/4,y,1);
			karelDivide(x/4+1,y,2);
		}
		else{
			fillings(x, y);
			karelDivide(x/4,y,3);
		}
	}
	public void xOddYEven(int x, int y){
		karelMove(x/2);
		turnLeft();
		putBeepers(y);
		turnLeft();
		karelMove(y/2+x/2-1);
		turnLeft();
		putBeepers(x);
		karelRotate();
		putBeepers(x);
	}
	public void xEvenYOdd(int x,int y){
		karelMove(x/2-1);
		turnLeft();
		putBeepers(y);
		karelRotate();
		putBeepers(y);
		turnLeft();
		karelMove(x/2+y/2-1);
		turnLeft();
		putBeepers(x);
	}
	public void bothOdd(int x,int y){
		karelMove(x/2);
		turnLeft();
		putBeepers(y);
		turnLeft();
		karelMove(x/2+y/2);
		turnLeft();
		putBeepers(x);
	}
	public void bothEven(int x,int y){
		karelMove(x/2-1);
		turnLeft();
		putBeepers(y/2-1);
		turnLeft();
		putBeepers(x/2);
		karelRotate();
		putBeepers(x/2-1);
		turnLeft();
		putBeepers(y/2);
		karelRotate();
		putBeepers(y/2-1);
		turnLeft();
		putBeepers(x/2);
		karelRotate();
		putBeepers(x/2-1);
		turnLeft();
		putBeepers(y/2);
	}
	public int getAxis(){
		int lenght = 1;
		while (frontIsClear()){
			moveMe();
			lenght ++;
		}
		turnLeft();
		return lenght;
	}
	public void moveMe(){
		move();
		totalMoves ++;
	}
	public void karelMove(int steps){
		int i =0;
		while (i < steps){
			if(frontIsBlocked())
				turnLeft();
				moveMe();
			i++;
		}
	}
	public void putBeepers(int beepers){
		int i =0;
		while (i < beepers){
			i++;
			if(beepersPresent()){
				moveMe();
			}
			else if (frontIsBlocked()){
				putBeeper();
			}
			else{
				putBeeper();
				moveMe();
			}
		}
	}
	public void karelRotate(){
		if(facingNorth()) {
			turnLeft();
			moveMe();
			turnLeft();
		} else if (facingWest()&&rightIsBlocked()) {
			turnLeft();
			moveMe();
			turnLeft();
		} else {
			turnRight();
			moveMe();
			turnRight();
		}
	}
	public void fillings(int x,int y){
		turnLeft();
		int i=0,filling=(x-3)%4;
		while (i<filling){
			i++;
			putBeepers(y);
			if(i<filling)
				karelRotate();
		}
		if(facingNorth())
			turnLeft();
		if(facingSouth())
			turnRight();
	}
	public void karelDivide(int x,int y,int Column){
		int i = 0;
			while (i < Column) {
				i++;
				if(frontIsClear()) {
					karelMove(x);
					if (rightIsClear())
						turnRight();
					else {
						turnLeft();
					}
					putBeepers(y);
					if (facingNorth() || facingWest())
						turnLeft();
					else
						turnRight();
				}
			}
	}
}