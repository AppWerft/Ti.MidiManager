# Ti.MidiManager

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