package com.amaterasu12.raidenredux.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.amaterasu12.raidenredux.RaidenRedux;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(RaidenRedux.W_WIDTH, RaidenRedux.W_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new RaidenRedux();
        }
}