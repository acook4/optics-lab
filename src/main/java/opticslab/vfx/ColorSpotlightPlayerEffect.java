package opticslab.vfx;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

public class ColorSpotlightPlayerEffect extends SpotlightPlayerEffect {

    public ColorSpotlightPlayerEffect (Color color)
    {
        super();
        this.color.set(color);
    }
}
