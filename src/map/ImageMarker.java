import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class ImageMarker extends AbstractMarker {

	PImage img;
	PApplet parent;

	public ImageMarker(Location location, PApplet p, PImage img) {
		super(location);
		this.parent=p;
		this.img = img;
	}

	@Override
	public void draw(PGraphics pg, float x, float y) {
		pg.pushStyle();
		pg.imageMode(PConstants.CORNER);
		// The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
		pg.image(img, x-5 , y-5,10,10);
		pg.popStyle();
	}

	@Override
	protected boolean isInside(float checkX, float checkY, float x, float y) {
		return checkX > x && checkX < x + img.width && checkY > y && checkY < y + img.height;
	}
}