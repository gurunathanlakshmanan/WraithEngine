package wraith.library.WorldManagement.TileGrid;

import java.awt.image.BufferedImage;
import wraith.library.MiscUtil.ImageUtil;

public class Chipset{
	private int scaleLevel;
	private ChipsetTileMaterial[] materials;
	private final int rows, cols, size;
	private final BufferedImage[][] tiles;
	public Chipset(BufferedImage img, int rows, int cols, int scales){
		if(img==null)throw new IllegalArgumentException("Image cannot be null!");
		if(rows<1||cols<1)throw new IllegalArgumentException("Rows and cols must be at least 1!");
		if(scales<1)throw new IllegalArgumentException("Scales must be at least equal to 1!");
		tiles=ImageUtil.splitImageAndScale(img, rows, cols, scales);
		this.cols=cols;
		this.rows=rows;
		size=tiles[0][0].getWidth();
	}
	public Chipset(BufferedImage img, int size, int scales){
		if(img==null)throw new IllegalArgumentException("Image cannot be null!");
		if(size<1)throw new IllegalArgumentException("Size cannot be less then 1!");
		if(scales<1)throw new IllegalArgumentException("Scales must be at least equal to 1!");
		this.cols=img.getWidth()/size;
		this.rows=img.getHeight()/size;
		this.size=size;
		tiles=ImageUtil.splitImageAndScale(img, rows, cols, scales);
	}
	public void generateTileMaterials(){
		materials=new ChipsetTileMaterial[tiles[0].length];
		int i;
		for(i=0; i<materials.length; i++)materials[i]=new ChipsetTileMaterial(this, i);
	}
	public void setScaleLevel(int scaleLevel){ this.scaleLevel=scaleLevel; }
	public BufferedImage getTile(int x, int y){ return tiles[scaleLevel][y*cols+x]; }
	public BufferedImage getTile(int index){ return tiles[scaleLevel][index]; }
	public TileMaterial getTileMaterial(int x, int y){ return materials[y*cols+x]; }
	public int getRows(){ return rows; }
	public int getCols(){ return cols; }
	public int getScaleLevel(){ return scaleLevel; }
	public int getScaleDepth(){ return tiles.length; }
	public int getSize(){ return size; }
}