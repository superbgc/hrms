package gc.domain;

import java.io.Serializable;

public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer id;  // ≤ø√≈±‡∫≈
    private String name;  // √˚≥∆
    private String description;  // √Ë ˆ 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Deparment [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
    
}
