package gc.domain;

import java.io.Serializable;

public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   private Integer id;  // Ö°Î»±àºÅ
	    private String name;  // Ãû³Æ
	    private String description;  // ÃèÊö
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
			return "Position [id=" + id + ", name=" + name + ", description=" + description + "]";
		}
		
}
