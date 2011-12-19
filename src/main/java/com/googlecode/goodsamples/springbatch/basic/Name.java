package com.googlecode.goodsamples.springbatch.basic;

public class Name {
	private Integer id;
	String name;

	public Name(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Name(Integer id) {
		this.id = id;
	}

	public Name(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer id() {
		return id;
	}
	
	public String name() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Name [id=" + id + ", name=" + name + "]";
	}
}
