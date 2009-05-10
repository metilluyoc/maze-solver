/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maze.gui.mazeeditor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.net.URL;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import maze.gui.CellSize;

/**
 *
 * @author desolc
 */
public class CrossTemplate extends MazeTemplate
{
   private TemplatePeg mCenter = null;
   private Point mCenterPoint = new Point(0,0);
   private static final int MIN_SIZE = 1;
   private int mSize = MIN_SIZE;
   public CrossTemplate()
   {
      URL iconResource = BoxTemplate.class.getResource("images/Cross.png");
      this.mIcon = new ImageIcon(iconResource);
      this.mDesc = "Cross";
      updateTemplate();
   }
   @Override
   public void nextOrientation(){}

   @Override
   public void grow()
   {
      mSize++;
      updateTemplate();
   }

   @Override
   public void shrink()
   {
      if (mSize > MIN_SIZE)
      {
         mSize--;
         updateTemplate();
      }
   }

   @Override
   public void draw(Graphics2D g, CellSize size)
   {
      g.translate(mCenterPoint.x-size.getWallWidthHalf(),
                  mCenterPoint.y-size.getWallHeightHalf());

      TreeSet<TemplatePeg> visited = new TreeSet<TemplatePeg>();
      drawPeg(mCenter,visited, g, size);
      g.translate(-(mCenterPoint.x-size.getWallWidthHalf()),
                  -(mCenterPoint.y-size.getWallHeightHalf()));
   }

   @Override
   public void reset()
   {
      if (mSize > MIN_SIZE)
      {
         mSize = MIN_SIZE;
         updateTemplate();
      }
   }

   public void updateTemplate()
   {
      mCenter = new TemplatePeg();
      TemplateWall newWall;
      TemplatePeg newPeg;
      TemplatePeg last = mCenter;

      for (int i = 0; i < mSize; i++)
      {
         newWall = new TemplateWall();
         last.top = newWall;
         newWall.mLeftBottom = last;
         newPeg = new TemplatePeg();
         newPeg.bottom = newWall;
         newWall.mRightTop = newPeg;
         last = newPeg;
      }

      last = mCenter;

      for (int i = 0; i < mSize; i++)
      {
         newWall = new TemplateWall();
         last.bottom = newWall;
         newWall.mRightTop = last;
         newPeg = new TemplatePeg();
         newPeg.top = newWall;
         newWall.mLeftBottom = newPeg;
         last = newPeg;
      }

      last = mCenter;

      for (int i = 0; i < mSize; i++)
      {
         newWall = new TemplateWall();
         last.right = newWall;
         newWall.mLeftBottom = last;
         newPeg = new TemplatePeg();
         newPeg.left = newWall;
         newWall.mRightTop = newPeg;
         last = newPeg;
      }

      last = mCenter;

      for (int i = 0; i < mSize; i++)
      {
         newWall = new TemplateWall();
         last.left = newWall;
         newWall.mRightTop = last;
         newPeg = new TemplatePeg();
         newPeg.right = newWall;
         newWall.mLeftBottom = newPeg;
         last = newPeg;
      }
   }

   @Override
   public TemplatePeg[] getCenterPegs()
   {
      return new TemplatePeg[]{mCenter};
   }

   @Override
   public Point[] getCenterPoints()
   {
      return new Point[]{mCenterPoint};
   }

   @Override
   public void updatePosition(Point p, CellSize size)
   {
      mCenterPoint = (Point)p.clone();
   }
}