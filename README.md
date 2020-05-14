# react-native-sip2
![npm version](https://badge.fury.io/js/react-native-sip2.svg)

**UPDATE**: Now is compatible with RN 0.60+ (iOS and AndroidX)

iOS - For RN 0.60+ you need to execute the following commands:

    yarn add react-native-sip
    cd ios
    pod install


## Support
- Currently support for iOS and Android.  
- Support video and audio communication.
- Ability to use Callkit and PushNotifications.
- You can use it to build an iOS/Android app that can communicate with SIP server.
- Android version is based on [react-native-sip2-builder](https://github.com/telefon-one/react-native-sip2-builder)
- iOS version is based on [Vialer-pjsip-iOS](https://github.com/VoIPGRID/Vialer-pjsip-iOS)

## To do

 - [x] Send SIP Messages (IM) iOS
 - [ ] Send SIP Messages (IM) Android
 -  [x] isTyping iOS
 - [ ] isTyping Android

## Installation

- [iOS](https://github.com/telefon-one/react-native-sip2/blob/master/docs/installation_ios.md)
- [Android](https://github.com/telefon-one/react-native-sip2/blob/master/docs/installation_android.md)

## Usage

First of all you have to initialize module to be able to work with it.

There are some interesting moment in initialization.
When application goes to background, sip module is still working and able to receive calls, but your javascipt is totally suspended.
When User open your application, javascript start to work and now your js application need to know what status have your account or may be you have pending incoming call.

So thats why first step should call start method for sip module.

```javascript
import {Endpoint} from 'react-native-sip2'

let endpoint = new Endpoint();
let state = await endpoint.start(); // List of available accounts and calls when RN context is started, could not be empty because Background service is working on Android
let {accounts, calls, settings, connectivity} = state;

// Subscribe to endpoint events
endpoint.on("registration_changed", (account) => {});
endpoint.on("connectivity_changed", (online) => {});
endpoint.on("call_received", (call) => {});
endpoint.on("call_changed", (call) => {});
endpoint.on("call_terminated", (call) => {});
endpoint.on("call_screen_locked", (call) => {}); // Android only
```

Account creating is pretty strainghforward.

```javascript
let configuration = {
  "name": "John",
  "username": "sip_username",
  "domain": "pbx.carusto.com",
  "password": "****",
  "proxy": null,
  "transport": null, // Default TCP
  "regServer": null, // Default wildcard
  "regTimeout": null // Default 3600
  "regHeaders": {
    "X-Custom-Header": "Value"
  },
  "regContactParams": ";unique-device-token-id=XXXXXXXXX"
};
endpoint.createAccount().then((account) => {
  console.log("Account created", account);
});

```

To be able to make a call first of all you should createAccount, and pass account instance into Endpoint.makeCall function.
This function will return a promise that will be resolved when sip initializes the call.

```javascript
let options = {
  headers: {
    "P-Assserted-Identity": "Header example",
    "X-UA": "React native"
  }
}

let call = await endpoint.makeCall(account, destination, options);
call.getId() // Use this id to detect changes and make actions

endpoint.addListener("call_changed", (newCall) => {
  if (call.getId() === newCall.getId()) {
     // Our call changed, do smth.
  }
}
endpoint.addListener("call_terminated", (newCall) => {
  if (call.getId() === newCall.getId()) {
     // Our call terminated
  }
}
```

## API