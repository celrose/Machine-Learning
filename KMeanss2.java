import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math.*;

public class KMeanss2 {

	public int k = 16;
	public int m = 16384;
	public List<Pixel> u = new ArrayList();
	public class Pixel {
		int red;
		int green;
		int blue;

		Pixel(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;

		}

		public void setRed(int red) {
			this.red = red;
		}

		public void setGreen(int green) {
			this.green = green;
		}

		public void setBlue(int blue) {
			this.blue = blue;
		}

		public int getRed(){
			return red;
		}

		public int getGreen(){
			return green;
		}

		public int getBlue(){
			return blue;
		}

		/*@Override
		public String toString(){
			System.out.println();
		}
		*/
	}

	public void extractValues(String filename) {
		try {
			File file = new File(filename);
			BufferedImage image = ImageIO.read(file);
			int clr = 0;
			int red, green, blue;

			List<Pixel> p = new ArrayList();
			int counter = 0;

			//extract the RGB values of the image
			for(int i = 0; i < 128; i++) {
				for(int j = 0; j < 128; j++) {
					clr = image.getRGB(i, j);
					red = (clr & 0x00ff0000) >> 16;
					green = (clr & 0x0000ff00) >> 8;
					blue = (clr & 0x000000ff);
					p.add(new Pixel(red, green, blue));
					counter++;
				}
			}

			//Randomly get the 16 centroids

			Random rand = new Random();

			for(int i = 0; i < k; i++) {	
				int randomNum = rand.nextInt((16383 - 0) + 1);
				Pixel ran = p.get(randomNum);
				u.add(ran);
			}

			//Cluster Assignment
			int r1, g1, b1;
			int r2, g2, b2;
			float distance;

			Pixel sample;
			List<Float> clusterAssignment = new ArrayList();
			List<Float> minimum = new ArrayList();
			List<Float> min_Cluster = new ArrayList();
			
			//u1-u16 arrays
			List<Pixel> u1 = new ArrayList();
			List<Pixel> u2 = new ArrayList();
			List<Pixel> u3 = new ArrayList();
			List<Pixel> u4 = new ArrayList();
			List<Pixel> u5 = new ArrayList();
			List<Pixel> u6 = new ArrayList();
			List<Pixel> u7 = new ArrayList();
			List<Pixel> u8 = new ArrayList();
			List<Pixel> u9 = new ArrayList();
			List<Pixel> u10 = new ArrayList();
			List<Pixel> u11 = new ArrayList();
			List<Pixel> u12 = new ArrayList();
			List<Pixel> u13 = new ArrayList();
			List<Pixel> u14 = new ArrayList();
			List<Pixel> u15 = new ArrayList();
			List<Pixel> u16 = new ArrayList();

			float minDistance = 0;
			int minIndex = 0;
			float sum = 0;
			float costJ = 0;

			for(int i = 0; i < m; i++) {
				sample = p.get(i);
				r1 = sample.getRed();
				g1 = sample.getGreen();
				b1 = sample.getBlue();
				for(int j = 0; j < u.size(); j++) {
					r2 = u.get(j).getRed();
					g2 = u.get(j).getGreen();
					b2 = u.get(j).getBlue();				

					distance = (int)Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2,2));
					minimum.add(distance);
				}

				minDistance = minimum.get(0);
				for(int c = 0; c < minimum.size(); c++) {
					// System.out.println(minimum.get(c));

					if(minimum.get(c) <= minDistance) {
						minIndex = c;
						minDistance = minimum.get(c);
					}
				}

				// System.out.println(minIndex);
				if(minIndex == 0) {
					p.set(i, u.get(0));
					u1.add(sample);
				} else if(minIndex == 1) {
					p.set(i, u.get(1));
					u2.add(sample);
				} else if(minIndex == 2) {
					p.set(i, u.get(2));
					u3.add(sample);
				} else if(minIndex == 3) {
					p.set(i, u.get(3));
					u4.add(sample);
				} else if(minIndex == 4) {
					p.set(i, u.get(4));
					u5.add(sample);
				} else if(minIndex == 5) {
					p.set(i, u.get(5));
					u6.add(sample);
				} else if(minIndex == 6) {
					p.set(i, u.get(6));
					u7.add(sample);
				} else if(minIndex == 7) {
					p.set(i, u.get(7));
					u8.add(sample);
				} else if(minIndex == 8) {
					p.set(i, u.get(8));
					u9.add(sample);
				} else if(minIndex == 9) {
					p.set(i, u.get(9));
					u10.add(sample);
				} else if(minIndex == 10) {
					p.set(i, u.get(10));
					u11.add(sample);
				} else if(minIndex == 11) {
					p.set(i, u.get(11));
					u12.add(sample);
				} else if(minIndex == 12) {
					p.set(i, u.get(12));
					u13.add(sample);
				} else if(minIndex == 13) {
					p.set(i, u.get(13));
					u14.add(sample);
				} else if(minIndex == 14) {
					p.set(i, u.get(14));
					u15.add(sample);
				} else if(minIndex == 15) {
					p.set(i, u.get(15));
					u16.add(sample);
				} else {}

				// System.out.println("Smallest value is: " + minDistance);	
				// System.out.println("Smallest value at index: " + minIndex);
				// System.out.println("Adding: " + minDistance);
				min_Cluster.add(minDistance);
				minimum.clear();

			}

			//get the Sum;
			for(int h = 0; h < min_Cluster.size(); h++) {
				sum = sum + min_Cluster.get(h);
			}
			
			//compute for the cost
			costJ = sum / m;
			int sad = 0;
			sad = u1.size() + u2.size() + u3.size() + u4.size() + u5.size() + u6.size() + u7.size() + u8.size() + u9.size() + u10.size() + u11.size() + u12.size() + u13.size() + u14.size() + u15.size() + u16.size();
			System.out.println("PLEASE WORK");
			System.out.println("u1 " + u1.size());
			System.out.println("u2 " + u2.size());
			System.out.println("u3 " + u3.size());
			System.out.println("u4 " + u4.size());
			System.out.println("u5 " + u5.size());
			System.out.println("u6 " + u6.size());
			System.out.println("u7 " + u7.size());
			System.out.println("u8 " + u8.size());
			System.out.println("u9 " + u9.size());
			System.out.println("u10 " + u10.size());
			System.out.println("u11 " + u11.size());
			System.out.println("u12 " + u12.size());
			System.out.println("u13 " + u13.size());
			System.out.println("u14 " + u14.size());
			System.out.println("u15 " + u15.size());
			System.out.println("u16 " + u16.size());
			System.out.println(sad);
			System.out.println("red: " + p.get(0).getRed());
			System.out.println("Green: " + p.get(0).getBlue());
			System.out.println("blue: " + p.get(0).getGreen());
			// System.out.println("Sum: " + sum);
			// System.out.println("Smallest values have size: " + min_Cluster.size());

			//create new image with new Pixel p values;

			BufferedImage image2 = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);;
			int ctr = 0;
			for(int i = 0; i < 128; i++) {
				for(int j = 0; j < 128; j++) {
					int rgb = new Color(p.get(ctr).getRed(), p.get(ctr).getGreen(), p.get(ctr).getBlue()).getRGB();
					image2.setRGB(i, j, rgb);
					ctr++;
				}
			}
			File outputfile = new File("test.png");
			ImageIO.write(image2, "png", outputfile);

		} catch(IOException c) {
			c.printStackTrace();
		}
	}

	public static void main(String[] args) {
		KMeanss2 kmeans = new KMeanss2();
		String filename = "kmimg1.png";
		kmeans.extractValues(filename);
	}
}