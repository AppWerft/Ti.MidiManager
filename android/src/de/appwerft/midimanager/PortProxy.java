/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.midimanager;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;


// This proxy can be created by calling Midimanager.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule=MidimanagerModule.class)
public class PortProxy extends KrollProxy
{
	// Standard Debugging variables
	private static final String LCAT = "ExampleProxy";
	private static final boolean DBG = TiConfig.LOGD;

	


	// Constructor
	public PortProxy()
	{
		super();
	}

	

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options)
	{
		super.handleCreationDict(options);

		if (options.containsKey("message")) {
			Log.d(LCAT, "example created with message: " + options.get("message"));
		}
	}

	// Methods
	@Kroll.method
	public void printMessage(String message)
	{
		Log.d(LCAT, "printing message: " + message);
	}


	@Kroll.getProperty @Kroll.method
	public String getMessage()
	{
        return "Hello World from my module";
	}

	@Kroll.setProperty @Kroll.method
	public void setMessage(String message)
	{
	    Log.d(LCAT, "Tried setting module message to: " + message);
	}
}
