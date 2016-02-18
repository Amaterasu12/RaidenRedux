package com.amaterasu12.raidenredux.Tools;

import com.amaterasu12.raidenredux.Components.PositionComponent;
import com.amaterasu12.raidenredux.RaidenRedux;

/**
 * Created by Carl on 2/18/2016.
 */
public class Tools {
    public enum DIRECTION {UP, DOWN, LEFT, RIGHT, NONE}

    public static DIRECTION outOfBoundsX(PositionComponent position, int padding){
        if (position.x < padding)
            return DIRECTION.LEFT;
        else if(position.x > RaidenRedux.W_WIDTH - padding)
            return DIRECTION.RIGHT;
        else
            return DIRECTION.NONE;
    }

    public static DIRECTION outOfBoundsY(PositionComponent position, int padding){
        if (position.y < padding)
            return DIRECTION.DOWN;
        else if(position.y > RaidenRedux.W_HEIGHT - padding)
            return DIRECTION.UP;
        else
            return DIRECTION.NONE;
    }
}
