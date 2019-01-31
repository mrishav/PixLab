
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  // Only keep the blue value
  //used zeroblue as basis 
  public void keepOnlyBlue () {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void negate () {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setBlue(255 - pixelObj.getBlue());
        pixelObj.setGreen(255 - pixelObj.getGreen());
      }
    }
  }
  
  public void grayscale () {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel p : rowArray)
      {
        int average = (p.getRed() + p.getBlue() + p.getGreen()) / 3;
        
        p.setRed(p.getBlue());
        p.setBlue(p.getBlue());
        p.setGreen(p.getBlue());
      }
    }
  }
  
  public void fixUnderwater () {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)//the colon means in/ fro-s o take the 1D array, row array from the pixels 2d array//pixels is the two day array
    	//saying 
    {
    	//
      for (Pixel pixelObj : rowArray)
    	  //just want to takw every pixel from the row array and call that pixelObj
      {
    	  //increase redvalue to make it easier
        pixelObj.setRed( pixelObj.getRed() * 5 );
      }
    }
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels.length;
    for (int row = 0; row < width / 2; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[width - 1 - row][col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels.length;
    for (int row = 0; row < width / 2; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[width - 1 - row][col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels.length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels.length; col++)
      {
        leftPixel = pixels[row][col];
        //rightPixel = pixels[width - 1 - row][col];
        rightPixel = pixels[col][row];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorArms()
  {
    int mirrorPoint = 210;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // Left arm
    for (int row = 158; row < mirrorPoint; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 103; col < 170; col++)
      {
        topPixel = pixels[row][col];      
        bottomPixel = pixels[mirrorPoint - row + mirrorPoint + 25][col - 5];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
    
    int mirrorPoint2 = 210;
    Pixel topPixel2 = null;
    Pixel bottomPixel2 = null;
    
    // Right arm
    for (int row = 171; row < mirrorPoint2; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 239; col < 294; col++)
      {
        topPixel2 = pixels[row][col];      
        bottomPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2 + 25][col + 5];
        bottomPixel2.setColor(topPixel2.getColor());
      }
    }
  }
  
  public void mirrorGull()
  {
    int mirrorPoint = 350;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 236; row < 327; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 236; col < 345; col++)
      {
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void copy(Picture fromPic, int startRow, int endRow, int startCol, int endCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < endRow; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < endCol;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  public void copy(Picture fromPic, int startRow, int endRow, int startCol, int endCol, int r, int c)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = startRow, toRow = r; 
      fromRow < endRow &&
         toRow < fromPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = startCol, toCol = c; 
           fromCol < endCol+1 &&
           toCol < fromPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  
  public void myCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    Picture caterpillar = new Picture("caterpillar.jpg");
    Picture flower2Normal = new Picture("flower2.jpg");
    Picture barbara = new Picture("barbaraS.jpg");
   
    
    flower1.keepOnlyBlue();
    flower2.grayscale();
    caterpillar.negate();
    barbara.negate();
    
    this.copy(caterpillar, 0, 150, 0, 329);
    this.copy(flower1, 175, 275, 25, 125,300,200);
    this.copy(flower2, 350, 450, 15, 115);
    this.copy(flower2Normal, 275, 375, 50, 150);
    this.copy(barbara, 175, 322, 166, 313);
   
    
    this.mirrorVertical();
    this.write("collage.jpg");
  }
 
	 
  public void f (Picture messagePic) {
    Pixel[][] pixes = getPixels2D();
    Pixel[][] messagePix = messagePic.getPixels2D();
    for (int x = 0; x < pixes.length; x++){
      for (int y = 0; y < pixes[x].length; y++){
        Pixel pix = pixes[x][y];
        if (pix.getRed() % 2 != 0){
          if (pix.getRed() == 0)
            pix.setRed(pix.getRed() + 1);
          else
            pix.setRed(pix.getRed() - 1);
        }
        
        if (messagePix[x][y].getRed() < 220){
          pix.setRed(pix.getRed() + 1);
        }
      }
    }
  }
  /** Method to replace the blue background with
   * the pixels in the newBack picture
   * @param newBack the picture to copy from
   */
 public void chromakey(Picture newBack)
 {
Pixel fromPixel = null;
Pixel toPixel = null;
Pixel[][] toPixels = this.getPixels2D(); 
Pixel[][] fromPixels = newBack.getPixels2D(); 
for(int row=0;row<toPixels.length;row++){
	for(int col=0;col<toPixels[row].length;col++){
		toPixel=toPixels[row][col];
		fromPixel=fromPixels[row][col];
		if(toPixel.getGreen()>110&&toPixel.getGreen()>toPixel.getRed()&&toPixel.getGreen()>toPixel.getBlue()&&toPixel.getRed()<150&&toPixel.getBlue()<170){
			toPixels[row][col].setColor(fromPixel.getColor());
		}

	}
	}
}


 
 
 
 
 
 
 
 
 
 
 
 
 
 /** Hide a black and white message in the current
   * picture by changing the red to even and then
   * setting it to odd if the message pixel is black
   * @param messagePict the picture with a message
   */
 public void encode(Picture messagePict)
 {
Pixel[][] messagePixels = messagePict.getPixels2D(); 
Pixel[][] currPixels = this.getPixels2D();
Pixel currPixel = null;
Pixel messagePixel = null;
  
for(int row=0;currPixels.length>row;row++){
	for(int col=0;currPixels[row].length>col;col++){
		currPixel=currPixels[row][col];
		messagePixel=messagePixels[row][col];
		if(currPixel.getRed()%2==1){
			//original picture is now even in terms of red
		currPixel.setRed(currPixel.getRed()-1);
		}
		
	if(messagePixel.getRed()<40){//if prenthesis less than 40 cuz parts of message wanted to encode werent pure black and were grey
		//here we are encoding message by setting original pixels to odd in red
		currPixel.setRed(currPixel.getRed()+1);
	}
	}
	
}

}
 /**
  * Method to decode a message hidden in the       
  * red value of the current picture
  * @return the picture with the hidden message
  */
 public Picture decode()
 {
   Pixel[][] pixels = this.getPixels2D();//encoded message
   int height = this.getHeight();
   int width = this.getWidth();
   Pixel currPixel = null;
Pixel messagePixel = null;
Picture messagePicture = new Picture(height,width); //creates new blank pic same height and width as encoded pic
Pixel[][] messagePixels = messagePicture.getPixels2D(); //new pixels array of all pixels in message picture
for(int row=0;pixels.length>row;row++){
	for(int col=0;pixels[row].length>col;col++){
		currPixel=pixels[row][col];
		messagePixel=messagePixels[row][col];//adjusts individual pixels of message pixels
		if(currPixel.getRed()%2==1){
			//sets all odd pixels to 0 where message would have been so now thats black in the message pixels
		messagePixel.setRed(0);
		messagePixel.setGreen(0);
		messagePixel.setBlue(0);
		
		}
	}}
return messagePicture;
}
  
  //// FROM ORIGINAL DOC ////
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
          [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    
    System.out.println(count);
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                   int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.mirrorDiagonal();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this


