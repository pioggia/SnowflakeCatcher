import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

Glow [] drifters;
public void setup()
{
  background(17, 20, 26);
  size(500, 500);
  drifters=new Glow[55];
  for (int a=0; a<drifters.length; a++)
  {
    drifters[a]=new Glow();
  }
}
public void draw()
{
  frameRate(10);
  for(int i=0; i<drifters.length; i++)
  {
    drifters[i].erase();
    drifters[i].lookDown();
    drifters[i].move();
    drifters[i].wrap();
    drifters[i].show();
  }
}
public void mouseDragged()
{
  strokeWeight(1);
  stroke(91, 219, 206, 50);
  line(mouseX,mouseY,pmouseX,pmouseY);
  strokeWeight(2);
  stroke(91, 180, 206, 8);
  line(mouseX, mouseY, pmouseX, pmouseY);
  strokeWeight(10);
  stroke(91, 150, 206, 5);
  line(mouseX, mouseY, pmouseX, pmouseY);
}
class Glow
{
  int siz, x, y, speed;
  boolean motion;
  Glow()
  {
    x=(int)(Math.random()*500);
    y=0;
    siz=(int)(Math.random()*5);
    speed=(int)(Math.random()*25)+2;
    motion=true;
  }
  public void show()
  {
    fill(253, 232, 117);
    noStroke();
    ellipse(x, y, siz, siz);
    fill(250, 189, 99, 35);
    ellipse(x, y, siz*2, siz*2);
    fill(243, 209, 249, 35);
    ellipse(x, y, siz*3, siz*3);
  }
  public void lookDown()
  {
    if(get(x, y+speed+siz) == color(91, 219, 206, 15) || get(x, y+speed+siz) == color(91, 180, 206, 8) || get(x, y+speed+siz) == color(91, 150, 206, 5))
    {
      motion=false;
    }
    else 
    {
      motion=true;
    }
  }
  public void erase()
  {
    fill(17, 20, 26);
    noStroke();
    ellipse(x, y, siz+2, siz+2);
    fill(17, 20, 26);
    ellipse(x, y, siz*2+2, siz*2+2);
    fill(17, 20, 26);
    ellipse(x, y, siz*3+2, siz*3+2);
  }
  public void move()
  {
    if (motion==true)
    {
      x=x+(int)(Math.random()*3)-1;
      y=y+speed;
    }
    if(motion==false)
    {
      x=x;
      y=y;
    }
  }
  public void wrap()
  {
    if(y>500)
    {
      y=0;
      x=(int)(Math.random()*500);
      speed=(int)(Math.random()*25)+2;
    }
  }
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
