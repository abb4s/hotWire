public class motor {

	static double A = 0.1;		// deghat...milimetr
	static double Y = 100*A;	// max height...cm
	private String code;
	// private int wireDeg;
	private float mPosition;

	// **********************************************
	public motor() {

		this.mPosition = 0;
	}

	// **********************************************
	public void mMoveToPos(double newMPosition) {
		if (newMPosition <= Y && newMPosition >= -Y) {
			if (newMPosition > this.mPosition)
				while (this.mPosition < newMPosition){ 
					this.mPosition += Math.abs(A);
					diviceCommunicator.sendDAtaM(this.code,A);
				}
			else if (newMPosition < this.mPosition)
				while (this.mPosition > newMPosition){
					this.mPosition -= Math.abs(A);
					diviceCommunicator.sendDAtaM(this.code,-A);
				}
		} else 
			System.out.println("position out of range!!!");
	}

	// **********************************************
	public void mReset() {
		mMoveToPos(Y);
	}
	

	// **********************************************
	public float getmPosition() {
		return mPosition;
	}
	//***********************************************
	public void setCode(String A){
		this.code=A;
	}
}
