import java.util.Scanner;

public class myCNC {
	static Scanner console = new Scanner(System.in);
	private stand S;
	private motor right;
	private motor left;
	private position startPos;
	private position finishPos;

	public myCNC() {
		S = new stand();
		right = new motor();
		right.setCode("vr@");
		left = new motor();
		left.setCode("vl@");
		startPos = new position();
		finishPos = new position();
	}

	// ****************************************
	public void setStartpos(double A, double B, double C) {
		startPos.setLm(A);
		startPos.setRm(B);
		startPos.setSt(C);
	}

	// ***************************************
	public void setFinaltpos(double A, double B, double C) {
		finishPos.setLm(A);
		finishPos.setRm(B);
		finishPos.setSt(C);
	}

	// ***************************************
	// ***************************************
	// ***************************************
	// ***************************************
	public void cutline() {
		if (right.getmPosition() != finishPos.getRm()
				&& S.getsPosition() != finishPos.getSt()) {
			try {
				setStates();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if(right.getmPosition()==finishPos.getRm()) 
			S.sMoveToPos(finishPos.getSt());
		else if(S.getsPosition()==finishPos.getSt())
			right.mMoveToPos(finishPos.getRm());
		System.out.println("your in position:\nX:" + S.getsPosition() + "\nY:"
				+ right.getmPosition());
	}
	// ***************************************
	public void continueCutting() {
		startPos.setRm(finishPos.getRm());
		startPos.setSt(finishPos.getSt());
		cutline();
	}

	// ***************************************
	private void setStates() throws InterruptedException {
		double x, y, m;
		if (Math.abs(finishPos.getRm() - right.getmPosition()) >= Math
				.abs(finishPos.getSt() - S.getsPosition())) {
			System.out.println("Y>X");
			m = (finishPos.getRm() - right.getmPosition())
					/ (finishPos.getSt() - S.getsPosition());

			x = startPos.getSt();
			y = m * (x - startPos.getSt()) + startPos.getRm();
			if (finishPos.getSt() > S.getsPosition()) {
				System.out.println("++");
				while (finishPos.getSt() > S.getsPosition()) {
					Thread.sleep(100);
					S.sMoveToPos(x);
					right.mMoveToPos(y);
					left.mMoveToPos(y);
					x += stand.B;
					y = m * (x - startPos.getSt()) + startPos.getRm();
				}
			} else {
				System.out.println("--");
				while (finishPos.getSt() < S.getsPosition()) {
					Thread.sleep(100);
					S.sMoveToPos(x);
					right.mMoveToPos(y);
					left.mMoveToPos(y);
					x -= stand.B;
					y = m * (x - startPos.getSt()) + startPos.getRm();
				}
			}

		} else if (Math.abs(finishPos.getRm() - right.getmPosition()) < Math
				.abs(finishPos.getSt() - S.getsPosition())) {
			System.out.println("X>Y");
			m = (finishPos.getSt() - S.getsPosition())
					/ (finishPos.getRm() - right.getmPosition());
			y = startPos.getRm();
			x = m * (y - startPos.getRm()) + startPos.getSt();

			if (finishPos.getRm() > right.getmPosition()) {
				System.out.println("++");
				while (finishPos.getRm() > right.getmPosition()) {
					Thread.sleep(100);
					right.mMoveToPos(y);
					left.mMoveToPos(y);
					S.sMoveToPos(x);
					y += motor.A;
					x = m * (y - startPos.getRm()) + startPos.getSt();
				}
			} else {
				System.out.println("--");
				while (finishPos.getRm() < right.getmPosition()) {
					Thread.sleep(100);
					right.mMoveToPos(y);
					left.mMoveToPos(y);
					S.sMoveToPos(x);
					y -= motor.A;
					x = m * (y - startPos.getRm()) + startPos.getSt();
				}
			}
		}
	}
	// ***************************************
}
