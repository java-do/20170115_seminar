package jp.javado.db;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.javado.exception.JavaDoDBException;
import jp.javado.jaxrs.pojo.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {

	private static String dbFilePath = "products.json";
	
	private static List<Product> products;

	/*
	private static void init() throws JavaDoDBException {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			products = mapper.readValue(
						ClassLoader.getSystemResourceAsStream(dbFilePath),
						new TypeReference<List<Product>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new JavaDoDBException(e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new JavaDoDBException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JavaDoDBException(e);
		}
	}
	*/

	// 本来は上をやりたいが今回は何もしない処理に変更する
	public static void init() {
		products = new ArrayList<>();
		products.add(new Product(1, "ジンギスカン", 4500));
		products.add(new Product(2, "石狩鍋", 4000));
		products.add(new Product(3, "ホタテ", 1200));
	}
	
	public static List<Product> selectAll() throws JavaDoDBException {
		init();
		return products;
	}
	
	public static Product select(int id) throws JavaDoDBException {
		init();
		for (Product p: products) {
			if (id == p.getId()) {
				return p;
			}
		}
		return null;
	}

	public static Product insert(Product product) {
		init();
		products.add(product);
		product.setId(products.size());
		return product;
	}
	
	public static int update(Product product) {
		init();
		int count = 0;
		for (Product p : products) {
			if ( p.getId() == product.getId() ) {
				p.setName(product.getName());
				p.setPrice(product.getPrice());
				count++;
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		//ClassLoader.getSystemResource(dbFilePath)
		//mapper.writeValue(, this.products);
		
		return count;
	}
	
	public static void delete(int id) {
		init();
		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			if ( p.getId() == id ) {
				products.remove(i);
				return;
			}
		}
	}
	
	public static void main(String... args) throws Exception {
		//String json = "[{\"name\": \"cake\",\"price\": \"180\"},{\"name\": \"orange juice\",\"price\": \"120\"}]";
		
		ObjectMapper mapper = new ObjectMapper();
		List<Product> products = mapper.readValue(ClassLoader.getSystemResourceAsStream("products.json"), new TypeReference<List<Product>>() {});
		System.out.println(products.get(0).getName());
		System.out.println(products.get(0).getPrice());
	}
	
}
