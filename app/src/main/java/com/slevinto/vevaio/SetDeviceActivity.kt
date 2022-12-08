package com.slevinto.vevaio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Device(var brandNum: Int, var type: String, var model: String)

open class SetDeviceActivity : AppCompatActivity() {

    private var deviceList = arrayListOf(

        //<editor-fold desc="devices">

        /* FitBit Smartwatch*/
        Device(1, "Watch", "Fitbit Ace"),
        Device(1, "Watch", "Fitbit Ace 2"),
        Device(1, "Watch", "Fitbit Ace 3"),
        Device(1, "Watch", "Fitbit Alta"),
        Device(1, "Watch", "Fitbit Alta HR"),
        Device(1, "Watch", "Fitbit Blaze"),
        Device(1, "Watch", "Fitbit Charge"),
        Device(1, "Watch", "Fitbit Charge 2"),
        Device(1, "Watch", "Fitbit Charge 3"),
        Device(1, "Watch", "Fitbit Charge 4"),
        Device(1, "Watch", "Fitbit Charge 4 SE"),
        Device(1, "Watch", "Fitbit Charge 5"),
        Device(1, "Watch", "Fitbit Charge HR"),
        Device(1, "Watch", "Fitbit Flex"),
        Device(1, "Watch", "Fitbit Flex 2"),
        Device(1, "Watch", "Fitbit Force"),
        Device(1, "Watch", "Fitbit Inspire"),
        Device(1, "Watch", "Fitbit Inspire 2"),
        Device(1, "Watch", "Fitbit Inspire HR"),
        Device(1, "Watch", "Fitbit Ionic"),
        Device(1, "Watch", "Fitbit Luxe"),
        Device(1, "Watch", "Fitbit One"),
        Device(1, "Watch", "Fitbit Sense"),
        Device(1, "Watch", "Fitbit Surge"),
        Device(1, "Watch", "Fitbit Ultra"),
        Device(1, "Watch", "Fitbit Versa"),
        Device(1, "Watch", "Fitbit Versa 2"),
        Device(1, "Watch", "Fitbit Versa 3"),
        Device(1, "Watch", "Fitbit Versa Lite"),
        Device(1, "Watch", "Fitbit Versa Special Edition"),
        Device(1, "Watch", "Fitbit Zip"),

        /* Garmin Smartwatch*/
        Device(2, "Watch", "Garmin Approach G7"),
        Device(2, "Watch", "Garmin Approach G8"),
        Device(2, "Watch", "Garmin Approach G12"),
        Device(2, "Watch", "Garmin Approach G80"),
        Device(2, "Watch", "Garmin Approach G30"),
        Device(2, "Watch", "Garmin Approach S12"),
        Device(2, "Watch", "Garmin Approach S20"),
        Device(2, "Watch", "Garmin Approach S4"),
        Device(2, "Watch", "Garmin Approach S40"),
        Device(2, "Watch", "Garmin Approach S42"),
        Device(2, "Watch", "Garmin Approach S5"),
        Device(2, "Watch", "Garmin Approach S6"),
        Device(2, "Watch", "Garmin Approach Z82"),
        Device(2, "Watch", "Garmin Approach S20"),
        Device(2, "Watch", "Garmin Approach S10"),
        Device(2, "Watch", "Garmin Approach S60"),
        Device(2, "Watch", "Garmin Approach S62"),
        Device(2, "Watch", "Garmin Approach X10"),
        Device(2, "Watch", "Garmin Approach X40"),
        Device(2, "Watch", "Garmin Captain Marvel"),
        Device(2, "Watch", "Garmin D2"),
        Device(2, "Watch", "Garmin D2 Air"),
        Device(2, "Watch", "Garmin D2 Bravo"),
        Device(2, "Watch", "Garmin D2 Bravo Titanium"),
        Device(2, "Watch", "Garmin D2 Charlie"),
        Device(2, "Watch", "Garmin D2 Delta"),
        Device(2, "Watch", "Garmin D2 Delta PX"),
        Device(2, "Watch", "Garmin D2 Delta S"),
        Device(2, "Watch", "Garmin Darth Vader"),
        Device(2, "Watch", "Garmin Descent"),
        Device(2, "Watch", "Garmin Descent MK1"),
        Device(2, "Watch", "Garmin Descent MK2"),
        Device(2, "Watch", "Garmin Descent MK2i"),
        Device(2, "Watch", "Garmin Descent MK2s"),
        Device(2, "Watch", "Garmin Edge 1000"),
        Device(2, "Watch", "Garmin Edge 1030"),
        Device(2, "Watch", "Garmin Edge 1030 Bontrager"),
        Device(2, "Watch", "Garmin Edge 1030 Plus"),
        Device(2, "Watch", "Garmin Edge 130"),
        Device(2, "Watch", "Garmin Edge 130 Plus"),
        Device(2, "Watch", "Garmin Edge 20"),
        Device(2, "Watch", "Garmin Edge 200"),
        Device(2, "Watch", "Garmin Edge 205"),
        Device(2, "Watch", "Garmin Edge 25"),
        Device(2, "Watch", "Garmin Edge 305"),
        Device(2, "Watch", "Garmin Edge 500"),
        Device(2, "Watch", "Garmin Edge 510"),
        Device(2, "Watch", "Garmin Edge 520"),
        Device(2, "Watch", "Garmin Edge 520 Plus"),
        Device(2, "Watch", "Garmin Edge 530"),
        Device(2, "Watch", "Garmin Edge 605"),
        Device(2, "Watch", "Garmin Edge 705"),
        Device(2, "Watch", "Garmin Edge 800"),
        Device(2, "Watch", "Garmin Edge 810"),
        Device(2, "Watch", "Garmin Edge 820"),
        Device(2, "Watch", "Garmin Edge 830"),
        Device(2, "Watch", "Garmin Edge Explore"),
        Device(2, "Watch", "Garmin Edge Explore 1000"),
        Device(2, "Watch", "Garmin Edge Explore 820"),
        Device(2, "Watch", "Garmin Edge Touring"),
        Device(2, "Watch", "Garmin Edge Touring+"),
        Device(2, "Watch", "Garmin Epix"),
        Device(2, "Watch", "Garmin eTrex 302 CHN"),
        Device(2, "Watch", "Garmin eTrex Touch"),
        Device(2, "Watch", "Garmin Fenix"),
        Device(2, "Watch", "Garmin Fenix 2"),
        Device(2, "Watch", "Garmin Fenix 3"),
        Device(2, "Watch", "Garmin Fenix 3 HR"),
        Device(2, "Watch", "Garmin Fenix 5"),
        Device(2, "Watch", "Garmin Fenix 5 Plus"),
        Device(2, "Watch", "Garmin Fenix 5S"),
        Device(2, "Watch", "Garmin Fenix 5S Plus"),
        Device(2, "Watch", "Garmin Fenix 5X"),
        Device(2, "Watch", "Garmin Fenix 5X Plus"),
        Device(2, "Watch", "Garmin Fenix 6"),
        Device(2, "Watch", "Garmin Fenix 6 Pro"),
        Device(2, "Watch", "Garmin Fenix 6 Pro Solar"),
        Device(2, "Watch", "Garmin Fenix 6 Sapphire"),
        Device(2, "Watch", "Garmin Fenix 6 Solar"),
        Device(2, "Watch", "Garmin Fenix 6s"),
        Device(2, "Watch", "Garmin Fenix 6S Pro"),
        Device(2, "Watch", "Garmin Fenix 6S Pro Solar"),
        Device(2, "Watch", "Garmin Fenix 6S Sapphire"),
        Device(2, "Watch", "Garmin Fenix 6S Solar"),
        Device(2, "Watch", "Garmin Fenix 6X Pro"),
        Device(2, "Watch", "Garmin Fenix 6X Pro Solar"),
        Device(2, "Watch", "Garmin Fenix 6X Sapphire"),
        Device(2, "Watch", "Garmin Fenix 7"),
        Device(2, "Watch", "Garmin Fenix 7 Solar"),
        Device(2, "Watch", "Garmin Fenix 7 Sapphire"),
        Device(2, "Watch", "Garmin Fenix Chronos"),
        Device(2, "Watch", "Garmin Fenix Pro"),
        Device(2, "Watch", "Garmin Fenix Solar"),
        Device(2, "Watch", "Garmin Fenix/tactix/D2"),
        Device(2, "Watch", "Garmin First Avenger"),
        Device(2, "Watch", "Garmin Forerunner 10"),
        Device(2, "Watch", "Garmin Forerunner 101"),
        Device(2, "Watch", "Garmin Forerunner 110"),
        Device(2, "Watch", "Garmin Forerunner 15"),
        Device(2, "Watch", "Garmin Forerunner 201"),
        Device(2, "Watch", "Garmin Forerunner 205"),
        Device(2, "Watch", "Garmin Forerunner 210"),
        Device(2, "Watch", "Garmin Forerunner 220"),
        Device(2, "Watch", "Garmin Forerunner 225"),
        Device(2, "Watch", "Garmin Forerunner 230"),
        Device(2, "Watch", "Garmin Forerunner 235"),
        Device(2, "Watch", "Garmin Forerunner 235L"),
        Device(2, "Watch", "Garmin Forerunner 245"),
        Device(2, "Watch", "Garmin Forerunner 245 Music"),
        Device(2, "Watch", "Garmin Forerunner 25"),
        Device(2, "Watch", "Garmin Forerunner 30"),
        Device(2, "Watch", "Garmin Forerunner 301"),
        Device(2, "Watch", "Garmin Forerunner 305"),
        Device(2, "Watch", "Garmin Forerunner 310XT"),
        Device(2, "Watch", "Garmin Forerunner 35"),
        Device(2, "Watch", "Garmin Forerunner 405"),
        Device(2, "Watch", "Garmin Forerunner 410"),
        Device(2, "Watch", "Garmin Forerunner 45"),
        Device(2, "Watch", "Garmin Forerunner 45 Plus"),
        Device(2, "Watch", "Garmin Forerunner 45S"),
        Device(2, "Watch", "Garmin Forerunner 50"),
        Device(2, "Watch", "Garmin Forerunner 55"),
        Device(2, "Watch", "Garmin Forerunner 610"),
        Device(2, "Watch", "Garmin Forerunner 620"),
        Device(2, "Watch", "Garmin Forerunner 630"),
        Device(2, "Watch", "Garmin Forerunner 645"),
        Device(2, "Watch", "Garmin Forerunner 645 Music"),
        Device(2, "Watch", "Garmin Forerunner 735XT"),
        Device(2, "Watch", "Garmin Forerunner 745"),
        Device(2, "Watch", "Garmin Forerunner 910XT"),
        Device(2, "Watch", "Garmin Forerunner 920XT"),
        Device(2, "Watch", "Garmin Forerunner 935"),
        Device(2, "Watch", "Garmin Forerunner 945"),
        Device(2, "Watch", "Garmin Forerunner 945 LTE"),
        Device(2, "Watch", "Garmin Foretrex 601"),
        Device(2, "Watch", "Garmin Foretrex 701"),
        Device(2, "Watch", "Garmin FR60"),
        Device(2, "Watch", "Garmin FR70"),
        Device(2, "Watch", "Garmin HRM-Pro"),
        Device(2, "Watch", "Garmin Instinct"),
        Device(2, "Watch", "Garmin Instinct Esports"),
        Device(2, "Watch", "Garmin Instinct Solar"),
        Device(2, "Watch", "Garmin Instinct Solar Camo"),
        Device(2, "Watch", "Garmin Instinct Solar Surf"),
        Device(2, "Watch", "Garmin Instinct Solar Tactical"),
        Device(2, "Watch", "Garmin Instinct Tactical"),
        Device(2, "Watch", "Garmin Instinct Tide ASIA"),
        Device(2, "Watch", "Garmin Lily"),
        Device(2, "Watch", "Garmin MARQ Adventurer"),
        Device(2, "Watch", "Garmin MARQ American Magic"),
        Device(2, "Watch", "Garmin MARQ Athlete"),
        Device(2, "Watch", "Garmin MARQ Aviator"),
        Device(2, "Watch", "Garmin MARQ Captain"),
        Device(2, "Watch", "Garmin MARQ Captain American Magic"),
        Device(2, "Watch", "Garmin MARQ Commander"),
        Device(2, "Watch", "Garmin MARQ Driver"),
        Device(2, "Watch", "Garmin MARQ Expedition"),
        Device(2, "Watch", "Garmin MARQ Golfer"),
        Device(2, "Watch", "Garmin Mercedes-Benz Venu"),
        Device(2, "Watch", "Garmin Montana 7xx"),
        Device(2, "Watch", "Garmin Oregon 7 Series"),
        Device(2, "Watch", "Garmin Oregon 739 CHN"),
        Device(2, "Watch", "Garmin quatix 3"),
        Device(2, "Watch", "Garmin quatix 5"),
        Device(2, "Watch", "Garmin quatix 6"),
        Device(2, "Watch", "Garmin quatix 6 Titanium"),
        Device(2, "Watch", "Garmin quatix 6X Solar"),
        Device(2, "Watch", "Garmin Rally 100/200"),
        Device(2, "Watch", "Garmin Rey"),
        Device(2, "Watch", "Garmin Rino 7 Series"),
        Device(2, "Watch", "Garmin Speed Sensor 2"),
        Device(2, "Watch", "Garmin Swim"),
        Device(2, "Watch", "Garmin Swim 2"),
        Device(2, "Watch", "Garmin Tactix"),
        Device(2, "Watch", "Garmin Tactix Bravo"),
        Device(2, "Watch", "Garmin Tactix Charlie"),
        Device(2, "Watch", "Garmin Tactix Delta"),
        Device(2, "Watch", "Garmin Tactix Delta Ballistic"),
        Device(2, "Watch", "Garmin Tactix Delta Solar"),
        Device(2, "Watch", "Garmin TruSwing"),
        Device(2, "Watch", "Garmin Vector 3"),
        Device(2, "Watch", "Garmin Venu"),
        Device(2, "Watch", "Garmin Venu 2"),
        Device(2, "Watch", "Garmin Venu 2S"),
        Device(2, "Watch", "Garmin Venu Sq"),
        Device(2, "Watch", "Garmin Venu Sq Music"),
        Device(2, "Watch", "Garmin VIRB Ultra 30"),
        Device(2, "Watch", "Garmin Vivoactive"),
        Device(2, "Watch", "Garmin Vivoactive 3"),
        Device(2, "Watch", "Garmin VivoActive 3 Daimler"),
        Device(2, "Watch", "Garmin Vivoactive 3 Element"),
        Device(2, "Watch", "Garmin Vivoactive 3 Music"),
        Device(2, "Watch", "Garmin Vivoactive 3t"),
        Device(2, "Watch", "Garmin Vivoactive 4"),
        Device(2, "Watch", "Garmin Vivoactive 4S"),
        Device(2, "Watch", "Garmin Vivoactive HR"),
        Device(2, "Watch", "Garmin Vivofit"),
        Device(2, "Watch", "Garmin Vivofit 2"),
        Device(2, "Watch", "Garmin Vivofit 3"),
        Device(2, "Watch", "Garmin Vivofit 4"),
        Device(2, "Watch", "Garmin Vivoki"),
        Device(2, "Watch", "Garmin Vivolife"),
        Device(2, "Watch", "Garmin Vivomove"),
        Device(2, "Watch", "Garmin Vivomove 3"),
        Device(2, "Watch", "Garmin Vivomove 3S"),
        Device(2, "Watch", "Garmin Vivomove HR"),
        Device(2, "Watch", "Garmin Vivomove Luxe"),
        Device(2, "Watch", "Garmin Vivomove Style"),
        Device(2, "Watch", "Garmin Vivosmart"),
        Device(2, "Watch", "Garmin Vivosmart 3"),
        Device(2, "Watch", "Garmin Vivosmart 4"),
        Device(2, "Watch", "Garmin Vivosmart HR"),
        Device(2, "Watch", "Garmin Vivosmart HR+"),
        Device(2, "Watch", "Garmin Vivosport"),
        Device(2, "Watch", "Garmin Vivovit 4"),

        /* Polar Smartwatch*/
        Device(3, "Watch", "Polar A300"),
        Device(3, "Watch", "Polar A360"),
        Device(3, "Watch", "Polar AXN500"),
        Device(3, "Watch", "Polar AXN700"),
        Device(3, "Watch", "Polar Beat"),
        Device(3, "Watch", "Polar Beat 2"),
        Device(3, "Watch", "Polar Coach"),
        Device(3, "Watch", "Polar CS200"),
        Device(3, "Watch", "Polar CS300"),
        Device(3, "Watch", "Polar CS400"),
        Device(3, "Watch", "Polar CS500"),
        Device(3, "Watch", "Polar CS600"),
        Device(3, "Watch", "Polar CS600X"),
        Device(3, "Watch", "Polar F11"),
        Device(3, "Watch", "Polar F55"),
        Device(3, "Watch", "Polar F6"),
        Device(3, "Watch", "Polar F7"),
        Device(3, "Watch", "Polar Flow for Club"),
        Device(3, "Watch", "Polar FT40"),
        Device(3, "Watch", "Polar FT60"),
        Device(3, "Watch", "Polar FT7"),
        Device(3, "Watch", "Polar FT80"),
        Device(3, "Watch", "Polar Grit X"),
        Device(3, "Watch", "Polar Grit X Pro"),
        Device(3, "Watch", "Polar Ignite"),
        Device(3, "Watch", "Polar Ignite 2"),
        Device(3, "Watch", "Polar Loop"),
        Device(3, "Watch", "Polar Loop 2"),
        Device(3, "Watch", "Polar Loop Crystal"),
        Device(3, "Watch", "Polar M200"),
        Device(3, "Watch", "Polar M400"),
        Device(3, "Watch", "Polar M430"),
        Device(3, "Watch", "Polar M450"),
        Device(3, "Watch", "Polar M460"),
        Device(3, "Watch", "Polar M600"),
        Device(3, "Watch", "Polar OH1"),
        Device(3, "Watch", "Polar RC3 GPS"),
        Device(3, "Watch", "Polar RCX3"),
        Device(3, "Watch", "Polar RCX5"),
        Device(3, "Watch", "Polar RS200"),
        Device(3, "Watch", "Polar RS300X"),
        Device(3, "Watch", "Polar RS400"),
        Device(3, "Watch", "Polar RS800"),
        Device(3, "Watch", "Polar RS800CX"),
        Device(3, "Watch", "Polar S410"),
        Device(3, "Watch", "Polar S510"),
        Device(3, "Watch", "Polar S520"),
        Device(3, "Watch", "Polar S610"),
        Device(3, "Watch", "Polar S610i"),
        Device(3, "Watch", "Polar S625X"),
        Device(3, "Watch", "Polar S710"),
        Device(3, "Watch", "Polar S710i"),
        Device(3, "Watch", "Polar S720i"),
        Device(3, "Watch", "Polar S725"),
        Device(3, "Watch", "Polar S725X"),
        Device(3, "Watch", "Polar Unite"),
        Device(3, "Watch", "Polar V650"),
        Device(3, "Watch", "Polar V800"),
        Device(3, "Watch", "Polar Vantage M"),
        Device(3, "Watch", "Polar Vantage M2"),
        Device(3, "Watch", "Polar Vantage V"),
        Device(3, "Watch", "Polar Vantage V Titan"),
        Device(3, "Watch", "Polar Vantage V2"),
        Device(3, "Watch", "Polar Verity Sense"),

        /* Withings Smartwatch*/
        Device(8, "Watch", "Withings Activité"),
        Device(8, "Watch", "Withings Activité Pop"),
        Device(8, "Watch", "Withings Activite Steel"),
        Device(8, "Watch", "Withings Aura"),
        Device(8, "Watch", "Withings Go"),
        Device(8, "Watch", "Withings Move"),
        Device(8, "Watch", "Withings Move ECG"),
        Device(8, "Watch", "Withings Pulse"),
        Device(8, "Watch", "Withings Pulse HR"),
        Device(8, "Watch", "Withings Scan Watch"),
        Device(8, "Watch", "Withings Scan Watch Horizon"),
        Device(8, "Watch", "Withings Sleep"),
        Device(8, "Watch", "Withings Sleep Analyser"),
        Device(8, "Watch", "Withings Steel"),
        Device(8, "Watch", "Withings Steel HR"),
        Device(8, "Watch", "Withings Steel HR Sport"),

        /*Omron Smartwatch */
        Device(16, "Watch","Omron Walking Style IV Blue"),

        /* Suunto SmartWatch */
        Device(17, "Watch", "Suunto 3"),
        Device(17, "Watch", "Suunto 3 Fitness"),
        Device(17, "Watch", "Suunto 5"),
        Device(17, "Watch", "Suunto 7"),
        Device(17, "Watch", "Suunto 9"),
        Device(17, "Watch", "Suunto 9 BARO"),
        Device(17, "Watch", "Suunto 9 Peak"),
        Device(17, "Watch", "Suunto 9 red bull X-Alps"),
        Device(17, "Watch", "Suunto AmbiT3 Peak"),
        Device(17, "Watch", "Suunto AmbiT3 Run"),
        Device(17, "Watch", "Suunto AmbiT3 Sport"),
        Device(17, "Watch", "Suunto AmbiT3 Vertical"),
        Device(17, "Watch", "Suunto Core"),
        Device(17, "Watch", "Suunto D4I"),
        Device(17, "Watch", "Suunto D4I Novo"),
        Device(17, "Watch", "Suunto D5"),
        Device(17, "Watch", "Suunto D6I Novo"),
        Device(17, "Watch", "Suunto DX"),
        Device(17, "Watch", "Suunto EON Core"),
        Device(17, "Watch", "Suunto EON Steel"),
        Device(17, "Watch", "Suunto Spartan Sport Wrist HR"),
        Device(17, "Watch", "Suunto Spartan Sport Wrist HR BARO"),
        Device(17, "Watch", "Suunto Spartan Trainer Wrist HR"),
        Device(17, "Watch", "Suunto Spartan Ultra"),
        Device(17, "Watch", "Suunto Traverse"),
        Device(17, "Watch", "Suunto Traverse Alpha"),
        Device(17, "Watch", "Suunto Vyper Novo"),
        Device(17, "Watch", "Suunto Zoop Novo"),

        /* Huawei Smartwatch*/
        Device(38, "Watch","Huawei Band 2"),
        Device(38, "Watch","Huawei Band 3"),
        Device(38, "Watch","Huawei Band 3 Pro"),
        Device(38, "Watch","Huawei Band 3e"),
        Device(38, "Watch","Huawei Band 4"),
        Device(38, "Watch","Huawei Band 4 Pro"),
        Device(38, "Watch","Huawei Band 4e"),
        Device(38, "Watch","Huawei Band 6"),
        Device(38, "Watch","Huawei Color Band A2"),
        Device(38, "Watch","Huawei TalkBand B3"),
        Device(38, "Watch","Huawei TalkBand B3 Lite"),
        Device(38, "Watch","Huawei TalkBand B5"),
        Device(38, "Watch","Huawei TalkBand B6"),
        Device(38, "Watch","Huawei Watch 2"),
        Device(38, "Watch","Huawei Watch 2 4G"),
        Device(38, "Watch","Huawei Watch 2 Classik"),
        Device(38, "Watch","Huawei Watch 2 Sport"),
        Device(38, "Watch","Huawei Watch 3"),
        Device(38, "Watch","Huawei Wtach 3 Pro"),
        Device(38, "Watch","Huawei Watch Active"),
        Device(38, "Watch","Huawei Watch Classic"),
        Device(38, "Watch","Huawei Watch Elite"),
        Device(38, "Watch","Huawei Watch Fit"),
        Device(38, "Watch","Huawei Watch GT"),
        Device(38, "Watch","Huawei Watch GT 2 Pro"),
        Device(38, "Watch","Huawei Watch GT 2e"),
        Device(38, "Watch","Porsche Design Huawei Watch GT 2"),

        /* Polar Ring */
        Device(3, "Ring","Polar H10"),
        Device(3, "Ring","Polar OH1"),

        /* Omron Ring */
        Device(16, "Ring","Omron Viva"),

        /* Oura Ring */
        Device(18, "Ring","Oura Ring (Heritage Model)"),
        Device(18, "Ring","Oura Ring (Balance Model)"),

        /* Garmin Scale */
        Device(2, "Scale","Garmin Index"),
        Device(2, "Scale","Garmin Index S2"),

        /* Polar Scale */
        Device(3, "Scale","Polar Balance"),

        /* Withings Scale */
        Device(8, "Scale","Withings Body"),
        Device(8, "Scale","Withings Body+"),
        Device(8, "Scale","Withings Body Analyzer"),
        Device(8, "Scale","Withings Body Cardio"),
        Device(8, "Scale","Withings Body Plus"),
        Device(8, "Scale","Withings WS-30 Wireless Scale"),

        /* FitBit Scale */
        Device(1, "Scale","Fitbit Aria Air"),
        Device(1, "Scale","Fitbit Aria 2"),
        Device(1, "Scale","Fitbit Aria"),

        /* Withings Medical */
        Device(8, "Medical","BPM Connect"),
        Device(8, "Medical","BPM Core"),
        Device(8, "Medical","Move ECG"),
        Device(8, "Medical","Withings BPM"),
        Device(8, "Medical","Withings BPM +"),
        Device(8, "Medical","Withings Thermo"),

        /* FitBit Medical */
        Device(1, "Medical","FitBit sense"),

        /* Omron Medical */
        Device(16, "Medical", "Omron 10 Series Upper Arm Blood Pressure Monitor (BP7450)"),
        Device(16, "Medical", "Omron 10 Series Upper Arm Blood Pressure Monitor (BP786 CAN)"),
        Device(16, "Medical","Omron 10 Series Upper Arm Blood Pressure Monitor (BP786 CANN)"),
        Device(16, "Medical","Omron 10 Series Upper Arm Blood Pressure Monitor (BP786)"),
        Device(16, "Medical","Omron 10 Series Upper Arm Blood Pressure Monitor (BP786N)"),
        Device(16, "Medical","Omron 10 Series Wireless Wrist Blood Pressure Monitor (BP653)"),
        Device(16, "Medical","Omron 10 Series+ Upper Arm Blood Pressure Monitor (BP791IT)"),
        Device(16, "Medical","Omron 5 Series Upper Arm Blood Pressure Monitor (BP7250)"),
        Device(16, "Medical","Omron 7 Series Upper Arm (BP761 CAN)"),
        Device(16, "Medical","Omron 7 Series Upper Arm (BP761 CANN)"),
        Device(16, "Medical","Omron 7 Series Upper Arm (BP761)"),
        Device(16, "Medical","FitBit sense"),
        Device(16, "Medical","Omron 7 Series Upper Arm (BP761N)"),
        Device(16, "Medical","Omron 7 Series Wireless Upper Arm Blood Pressure Monitor (BP7350)"),
        Device(16, "Medical","Omron 7 Series Wireless Wrist Blood Pressure Monitor (BP6350)"),
        Device(16, "Medical","Omron 7 Series Wireless Wrist Blood Pressure Monitor (BP654)"),
        Device(16, "Medical","Omron Complete Wireless Upper Arm Blood Pressure Monitor + EKG (BP7900)Omron Evolv Wireless Upper Arm Blood Pressure Monitor (BP7000)"),
        Device(16, "Medical","Omron Evolve HEM-7600T-E"),
        Device(16, "Medical","Omron Gold Wireless Upper Arm Blood Pressure Monitor (BP5350)"),
        Device(16, "Medical","Omron Gold Wireless Wrist Blood Pressure Monitor (BP4350)"),
        Device(16, "Medical","Omron Heart Guide"),
        Device(16, "Medical","Omron Intelli Wrap Band"),
        Device(16, "Medical","Omron M400 Intelli IT HEM-7155T-D"),
        Device(16, "Medical","Omron ReliOn Automatic Blood Pressure Monitor"),
        Device(16, "Medical","Omron RS3 Intelli IT HEM-6161Y-D"),
        Device(16, "Medical","Omron RS7 Intelli IT HEM-6232T-D"),
        Device(16, "Medical","Omron Silver Wireless Upper Arm Blood Pressure Monitor (BP5250)"),
        Device(16, "Medical","Omron X4 Smart HEM-7155T-ESL"),
        Device(16, "Medical","Omron X7 Smart HEM-7361T-ESL"),

        //</editor-fold>
    )

    private lateinit var spinnerType: Spinner
    private lateinit var spinnerModel: Spinner
    private lateinit var btnContinue: Button
    private lateinit var btnSkip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_device)

        spinnerType = findViewById(R.id.spinner_type)
        val adapterType = ArrayAdapter(this, android.R.layout.simple_spinner_item, deviceList.map { it.type }.toSet().toList())
        spinnerType.adapter = adapterType

        spinnerModel = findViewById(R.id.spinner_model)
        val adapterModel = ArrayAdapter(this, android.R.layout.simple_spinner_item, deviceList.map { it.model })
        adapterModel.setNotifyOnChange(true)
        spinnerModel.adapter = adapterModel
        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedType = deviceList.map { it.type }.toSet().toList()[position]
                val modelList = deviceList.filter { it.type == selectedType }.map { it.model }
                adapterModel.clear()
                adapterModel.addAll(modelList)
            }
        }

        btnContinue = findViewById(R.id.btn_continue)
        btnSkip = findViewById(R.id.btn_skip)
        btnContinue.setOnClickListener {

            val i = Intent(this, AddCaregiverActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
            finish()
        }

        btnSkip.setOnClickListener {
            val i = Intent(this, AddCaregiverActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
            finish()
        }
    }

    companion object {

        fun AppCompatActivity.showToast(text: String, completion: () -> Unit) {
            runOnUiThread {
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                completion()
            }
        }

        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}