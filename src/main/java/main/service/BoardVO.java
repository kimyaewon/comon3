package main.service;

public class BoardVO {
	// BoardVO : 데이터값을 처리하는 객체 
	// 캡슐화 : 접근지정자
	private int unq;
	@Override
	public String toString() {
		return "BoardVO [unq=" + unq + ", title=" + title + ", pass=" + pass + ", name=" + name + ", content=" + content
				+ ", hits=" + hits + ", rdate=" + rdate + "]";
	}
	private String title;
	private String pass;
	public String name; // test 
	public String content;
	public int hits;
	public String rdate;
	
	private int viewPage = 1;
	private int startIndex = 1;
	private int endIndex = 10;
	
	
	// get/set 메소드 처리
	
	
	public int getViewPage() {
		return viewPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}

	public int getUnq() {
		return unq;
	}
	public void setUnq(int unq) {
		this.unq = unq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	
	
	
	
	
	
}