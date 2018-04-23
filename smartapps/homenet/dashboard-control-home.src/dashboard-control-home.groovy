/**
 *  Dashboard Control Home
 *
 *  Copyright 2018 Javier Larrondo
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Dashboard Control Home",
    namespace: "HomeNet",
    author: "Javier Larrondo",
    description: "Control de dispositivos de casa ",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png") {
    appSetting "Key"
    appSetting "Secrets"
}


preferences {
	section("Select Switch to monitor"){
		input "theSwitch", "capability.switch"
	}
	section("Which light(s) to dim to Level 1:") {
		input "switches1", "capability.switchLevel", multiple: true, required: false
	}
    section("Level 1"){
    	input "lvl1", "number", required: false
    }
	section("Which light(s) to dim to Level 2:") {
		input "switches2", "capability.switchLevel", multiple: true, required: false
	}
    section("Level 2"){
    	input "lvl2", "number", required: false
    }
    section("Turn ON these lights..."){
		input "switch1", "capability.switch", multiple: true, required: false
	}
    section("Turn OFF these lights..."){
		input "switch2", "capability.switch", multiple: true, required: false
	}
}

def installed()
{
	subscribe(app, appTouch)
        initialize()
}

def updated()
{
	unsubscribe()
	subscribe(app, appTouch)
        initialize()
}


def appTouch(evt) {
	log.info evt.value
    if (lvl1){
    	switches1.setLevel(lvl1.value)
    }
    if (lvl2){
		switches2.setLevel(lvl2.value)
    }
    if(switch1){
    	switch1.on()
    }
    if(switch2){
    	switch2.off()
    }
    theSwitch.off()

}

def onHandler(evt) {
	log.info evt.value
    if (lvl1){
    	switches1.setLevel(lvl1.value)
    }
    if (lvl2){
		switches2.setLevel(lvl2.value)
    }
    if(switch1){
    	switch1.on()
    }
    if(switch2){
    	switch2.off()
    }
    theSwitch.off()
}

def offHandler(evt) {
	log.debug "Received off from ${theSwitch}"
}

def initialize() {
	subscribe(theSwitch, "switch.On", onHandler)
}
// TODO: implement event handlers