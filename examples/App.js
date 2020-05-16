
import React, { Component } from 'react';

import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

import { Endpoint as sipEndpoint } from 'react-native-sip2'

export default class App extends Component {
  constructor() {
    super();
  }
  
  async componentDidMount() {
    await this.sEndpointInit();
  }

  async sEndpointInit() {
    console.log("sEndpointInit()");
    this.sEndpoint = new sipEndpoint;

    let configuration = {
      "name": "MyUserName",

      "username": "50363",
      "password": "pass50363",
      "domain": "sip.zadarma.com",
      "regServer": "",
      //"regServer": "sip.zadarma.com", // Default wildcard

      /*
      "username": "50363",
      "password": "pass50363",
      "domain": "172.16.104.17",
      "regServer": "",
      //"regServer": "172.16.104.17", // Default wildcard
*/

      "proxy": null,
      "transport": "UDP",//null, // Default TCP
      
      "regTimeout": 3600, // Default 3600
      "regHeaders": {
        //"X-Custom-Header": "Value"
      },
      //"regContactParams": ";unique-device-token-id=XXXXXXXXX",
      "regOnAdd": true,  // Default true, use false for manual REGISTRATION

      service: {
        ua: "siptest",
        stun: ['stun.l.google.com:19302', 'stun4.l.google.com:19302']
      },

      network: {
        useAnyway: true,           // Default: true
        useWifi: true,              // Default: true
        use3g: true,                // Default: false
        useEdge: true,             // Default: false
        useGprs: true,             // Default: false
        useInRoaming: true,        // Default: false
        useOtherNetworks: true      // Default: false
      }
    };

    let state = await this.sEndpoint.start();
    console.log("sEndpoint started");

    let { accounts, calls, settings, connectivity } = state;
    console.log("accounts:\n", accounts);
    console.log("calls:\n", calls);
    console.log("settings:\n", settings);
    console.log("connectivity:\n", connectivity);

    try {
      console.log("endpoint.createAccount");
      this.account = await this.sEndpoint.createAccount(configuration);
      console.log("account created", this.account);
    } catch (err) {
      console.log("err");
      console.error(err);
    }





    // Subscribe to endpoint events
    this.sEndpoint.on("registration_changed", (account) =>  {
      console.log("registration_changed", account);

      let options = {
        headers: {
          "P-Assserted-Identity": "Header example",
          "X-UA": "React native"
        }
      }
      
      let call =  this.sEndpoint.makeCall(account, "79214334799", options);


    });
    this.sEndpoint.on("connectivity_changed", (online) => {
      console.log("connectivity_changed", online);
    });
    this.sEndpoint.on("call_received", (call) => {
      console.log("call_received", call);
      this.call=call;
      this.onCallReceived(call)
    });
    this.sEndpoint.on("call_changed", (call) => {
      console.log("call_changed", call);
      //this.onCallTerminated(call);
      //Если из SIP пришел сигнал повесить трубку - шлем intent в наш Dialer
    });
    this.sEndpoint.on("call_terminated", (call) => {
      console.log("call_terminated", call);
      //this.onCallTerminated(call);
      //Если из SIP пришел сигнал повесить трубку - шлем intent в наш Dialer
    });
    this.sEndpoint.on("call_screen_locked", (call) => {
      console.log("call_screen_locked", call);
    }); // Android only
  }


  progress = () => {
    console.log("pjsip->progress");
  }

  answer = () => {
    console.log("pjsip->answer");
    let options = {};
    let promise = this.endpoint.answerCall(this.call, options);
    promise.then(() => {
      console.log('Answer complete');
    }).catch((e) => {
      console.error('Answer failed, show error', e);
    });
  }

  hangup = () => {
    console.log("pjsip->hangup");
    this.endpoint.hangup();
  }

  destroy = () => {
    this.endpoint.destroy();
  }

  render() {



    return (
      <>
        <StatusBar barStyle="dark-content" />
        <SafeAreaView>
          <ScrollView
            contentInsetAdjustmentBehavior="automatic"
            style={styles.scrollView}>
            <Header />
            {global.HermesInternal == null ? null : (
              <View style={styles.engine}>
                <Text style={styles.footer}>Engine: Hermes</Text>
              </View>
            )}
            <View style={styles.body}>
              <View style={styles.sectionContainer}>
                <Text style={styles.sectionTitle}>Step One</Text>
                <Text style={styles.sectionDescription}>
                  Edit <Text style={styles.highlight}>App.js</Text> to change this
                  screen and then come back to see your edits.
              </Text>
              </View>
              <View style={styles.sectionContainer}>
                <Text style={styles.sectionTitle}>See Your Changes</Text>
                <Text style={styles.sectionDescription}>
                  <ReloadInstructions />
                </Text>
              </View>
              <View style={styles.sectionContainer}>
                <Text style={styles.sectionTitle}>Debug</Text>
                <Text style={styles.sectionDescription}>
                  <DebugInstructions />
                </Text>
              </View>
              <View style={styles.sectionContainer}>
                <Text style={styles.sectionTitle}>Learn More</Text>
                <Text style={styles.sectionDescription}>
                  Read the docs to discover what to do next:
              </Text>
              </View>
              <LearnMoreLinks />
            </View>
          </ScrollView>
        </SafeAreaView>
      </>
    );
  };
}

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
});


