package web.jdbc.entity;

public class Emp {
	private String id;
	private Integer empno;
	private String ename;
	private Double sal;
	private Integer deptno;

	public Emp() {
		super();
	}

	public Emp(Integer empno, String ename, Double sal, Integer deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.deptno = deptno;
	}
	
	public Emp(String id, Integer empno, String ename, Double sal, Integer deptno) {
		super();
		this.id = id;
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Emp [deptno=" + deptno + ", empno=" + empno + ", ename="
				+ ename + ", id=" + id + ", sal=" + sal + ", getDeptno()="
				+ getDeptno() + ", getEmpno()=" + getEmpno() + ", getEname()="
				+ getEname() + ", getId()=" + getId() + ", getSal()="
				+ getSal() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
}
