package api.utility;

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
   //PET Module
	public static final String addPet="/pet";
	public static final String uploadPetImage="/pet/{petId}/uploadImage";
	public static final String getPetDetails="/pet/{petId}";
	public static final String updatePetDetails="/pet";
	public static final String findByStatus="/pet/findByStatus";
	public static final String updatePetDetailsByid="/pet/{petId}";
	public static final String deletePet="/pet/{petId}";
	
	//STORE Module
	public static final String placePetOrder="/store/order";
	public static final String getInventoryDetails="/store/inventory";
	
}
