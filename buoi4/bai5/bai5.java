package buoi4.bai5;

public class bai5 {

	public static void main(String[] args) {
		String data ="Java,Python,C++,PHP,Javascript";

		String [] languages = data.split(",");
		System.out.println("\nMảng sau khi tách: ");

		for (int i = 0; i<languages.length;i++){
			System.out.println(i+ ":" +languages[i]);
		}
		
		System.out.println("Các phàn tử bắt đầu bằng Java");
		for (int i = 0;i<languages.length;i++){
			if (languages[i].startsWith("Java")){
				System.out.println("- "+languages[i]);
			}
		}

		int position = data.indexOf('P');
		System.out.println("Vị trí đầu tiên của 'P': "+position);

	}
}