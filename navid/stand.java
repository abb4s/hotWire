public class stand {
	
	static double B = 0.1;		// deghat...milimetr
	static double X = 100*B;	// max length...cm
	private float sPosition;

	// *******************************************
	public stand() {
		sPosition = 0;
	}

	// *******************************************
	public void sMoveToPos(double newSPosition) {
		if (newSPosition <= X && newSPosition >= -X) {
			if (newSPosition > this.sPosition)
				while (this.sPosition < newSPosition) {
					this.sPosition += Math.abs(B);
					diviceCommunicator.sendDAtaS(B);
				}
			else if (newSPosition < this.sPosition)
				while (this.sPosition > newSPosition) {
					this.sPosition -=Math.abs(B);
					diviceCommunicator.sendDAtaS(-B);
				}
		} else {
			System.out.println("position out of range!!!");
		}
	}
	// *******************************************
	
	public void sReset(){
		sMoveToPos(X);
	}
	// *******************************************
	public float getsPosition() {
		return sPosition;
	}
}
