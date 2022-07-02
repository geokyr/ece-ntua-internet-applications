package pack;

public class Cls {
	
	private final Integer id;
	private final String code;
	private final String name;
	private final Float price;
	
	public Cls(Integer id, String code, String name, Float price) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		//  We could check() if our data is valid
	}
	
	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
	
	public String asString() {
		return code + " : " + name + " --> " + price;
	}
	
	@Override
	public String toString() {
		return "Data [code=" + code + ", name=" + name + ", price=" + price + "]";
	}
}