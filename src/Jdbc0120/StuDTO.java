package Jdbc0120;

// DTO (Data Transfer Object)
// : 데이터 전송 객체
// : 클래스 필드, 메소드, 생성자
public class StuDTO {

	private String stuName;
	private int stuAge;
	
	
	// getter, setter, toString, 생성자
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	
	
	
	@Override
	public String toString() {
		return "StuDTO [stuName=" + stuName + ", stuAge=" + stuAge + "]";
	}
	
	
	
	// 기본 생성자
	public StuDTO() {
	}
		
	// 매개변수 (파라미터) 생성자
	public StuDTO(String stuName, int stuAge) {
		this.stuName = stuName;
		this.stuAge = stuAge;
	}
	
	
	
	
	
	
	
}






