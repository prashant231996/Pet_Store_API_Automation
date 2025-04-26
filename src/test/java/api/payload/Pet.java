package api.payload;

import java.util.ArrayList;
import java.util.List;

public class Pet {
	
	private int id;
	private String name;
	private String status;
	private Category category;
	private List<Tag> tags;
	private List<Object>photoUrls=new ArrayList<Object>();
	
	public List<Object> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<Object> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
	

}
