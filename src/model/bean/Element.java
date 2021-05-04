package model.bean;

public class Element {

	private int id, parent_id;
	private String name, type, extension, content, created_at, updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public Element(int id, int parent_id, String name, String type, String extension, String content, String created_at,
			String updated_at) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.name = name;
		this.type = type;
		this.extension = extension;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
