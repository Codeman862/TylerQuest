package Sprites;

import java.io.IOException;

public class bloodSprite extends Scripts.Prefab {
	public bloodSprite(int x, int y, int width, int height) throws IOException {
		// constructor
		// this.assetPath="C:\\Users\\Janet\\git\\blood and mud\\blood_mud\\src\\blood_mud\\Scripts\\Assests\\treetopdown.jpg";
		this.assetPath = "C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\Tree\\blood.jpg";
		this.x = x;
		this.y = y;

		this.height = height;
		this.width = width;
		this.defaultImage = get_image(assetPath);
	}
}
