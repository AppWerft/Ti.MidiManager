# Ti.MidiManager

<img src="https://upload.wikimedia.org/wikipedia/commons/b/bf/MIDI_LOGO.jpg" width=300 />
<img src="http://mfg.fhstp.ac.at/cms/wp-content/uploads/2018/06/2.jpg" width=300 />

This Titanium module provides an interface for sending and receiving messages using the standard MIDI event protocol over USB, Bluetooth LE, and virtual (inter-app) transports.

### Overview

The Android MIDI package allows users to:

 *   Connect a MIDI keyboard to Android to play a synthesizer or drive music apps.
*    Connect alternative MIDI controllers to Android.
*    Drive external MIDI synths from Android.
    Drive external peripherals, lights, show control, etc from Android.
*    Generate music dynamically from games or music creation apps.
*    Generate MIDI messages in one app and send them to a second app.
*    Use an Android device running in peripheral mode as a multitouch controller connected to a laptop.


### Declare Feature in Manifest

An app that requires the MIDI API should declare that in the AndroidManifest.xml file. Then the app will not appear in the Play Store for old devices that do not support the MIDI API.

```xml
<uses-feature android:name="android.software.midi" android:required="true"/>
```
### Check for Feature Support

```js
const Midi = require('de.appwerft.midimanager');
Midi.hasSystemFeature();
```

### Get List of Already Plugged In Entities

When an app starts, it can get a list of all the available MIDI devices. This information can be presented to a user, allowing them to choose a device.

```js
const devices = Midi.getDevices();
``` 

### Notification of MIDI Devices HotPlug Events

The application can request notification when, for example, keyboards are plugged in or unplugged.

```js
Midi.onDevicedAdded = function() {
};
Midi.onDevicedRemoved = function() {
};
```
### Open a MIDI Device

To access a MIDI device you need to open it first. The open is asynchronous so you need to provide a callback for completion. 

```js
Midi.openDevice(device,function(d) {
	// do something with this device
}
```

### Open a MIDI Input Port
If you want to send a message to a MIDI Device then you need to open an “input” port with exclusive access.

```js
const port = device.openInputPort(index);
```

### Send a NoteOn
MIDI messages are sent as byte arrays. Here we encode a NoteOn message.

```js
// inside `opendevice():`
const port = device.openInputPort(index);
port.send({
	channel : 3, // 0…15
	offset  : 0,
	numBytes : numBytes,
	payload : [0x90,60,127] // note on, middle c, max. velocity 
});
```

## Using MIDI Over Bluetooth LE
Request Location Permission for BTLE

Applications that scan for Bluetooth devices must request permission in the manifest file. This LOCATION permission is required because it may be possible to guess the location of an Android device by seeing which BTLE devices are nearby.

 ```
 <uses-permission android:name="android.permission.BLUETOOTH"/>
 <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 ```

Apps must also request location permission from the user at run-time. 

## Scan for MIDI Devices

The app will only want to see MIDI devices and not mice or other non-MIDI devices. So construct a ScanFilter using the UUID for standard MIDI over BTLE.

```
MIDI over BTLE UUID = "03B80E5A-EDE8-4B33-A751-6CE34EC4C700"
```
For this task you can use the Titanium module `de.appwerft.bluetoothmanager`:

```
const BLE = require('de.appwerft.bluetoothmanager').BLE;
if (BLE.isBleSupported()) {
	BLE.startScan({
		onfound : function(e) {
			console.log(e.device);
			console.log(e.name);
			console.log(e.address);
			console.log(e.rssi);
			console.log(e.type);
		},
		onstatechanged : function(e) {
		},
		onerror : function(e) {
		}
	});
}
```
## Open a MIDI Bluetooth Device

<img src="https://cdn-learn.adafruit.com/assets/assets/000/066/491/medium800/bluefruit___ble_BLE_MIDI.jpg?1543295718" width=600 />


```js
const MIDI = require("de.appwerft.midimanager");
const BLE = require('de.appwerft.bluetoothmanager').BLE;

BLE.startScan({
		onfound : function(e) {
			MIDI.openBluetoothDevice(e.device);
		}
});
	
```
There are more parameters for scanner, i.e filter for devices. See more at [repo Ti.BluetoothManager](https://github.com/AppWerft/Ti.BluetoothManager#bluetoothle)


Once the MIDI/BTLE device has been opened by one app then it will also become available to other apps using the MIDI device discovery calls described above. (https://developer.android.com/reference/android/media/midi/package-summary)