package tiles;

public class MatrixPoint {
	private int column;
	private int row;
	
	public MatrixPoint(){
		this(0,0);
	}
	public MatrixPoint(int column, int row){
		this.column = column;
		this.row = row;
	}
	public boolean equals(MatrixPoint p){
		return (this.column == p.getColumn() && this.row == p.getRow())? true: false;
	}
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
}
