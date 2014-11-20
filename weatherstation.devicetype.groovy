**
 *  Weather Station
 *
 *  Author: yracine66@gmail.com
 *  Date: 2014-06-04
 
 * Copyright (C) jnovack@gmail.com
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following
 * conditions: The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

preferences {
    input("zipcode", "text", title: "ZipCode", description: "ZipCode of Forecast (leave blank to autodetect)")
}

metadata {
    definition (name: "Weather Station", author: "yracine66@gmail.com") {
      capability "Relative Humidity Measurement"
      capability "Temperature Measurement"
      capability "Water Sensor"
      capability "Polling"
      
      attribute "snow", "string"
      attribute "water", "string"
      attribute "feels_like", "string"
      attribute "wind_direction", "string"
      attribute "wind_speed", "string"
      attribute "uv_index", "string" 
      attribute "forecast", "string" 
      attribute "precipitation", "string" 
      attribute "location", "string" 
      
      
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles {
        // First Row
        valueTile("temperature", "device.temperature", width: 1, height: 1, inactiveLabel: false,canChangeIcon: true) {
            state("temperature", label: 'Outside\n${currentValue}°', unit:"C", backgroundColors: [
                     [value: 0, color: "#153591"],
    	             [value: 8, color: "#1e9cbb"],
                     [value: 14, color: "#90d2a7"],
            	     [value: 20, color: "#44b621"],
                     [value: 24, color: "#f1d801"],
                     [value: 29, color: "#d04e00"],
                     [value: 36, color: "#bc2323"]           
                 ]
            )
        }
        valueTile("humidity", "device.humidity", decoration: "flat",inactiveLabel: false) {
            state "default",  label:'Humidity\n${currentValue}%', unit: "Humidity"
        }
        valueTile("feels_like", "device.feels_like", decoration: "flat",inactiveLabel: false) {
            state "feels_like", label: 'FeelsLike\n${currentValue}º'
        }

        // Second Row
        standardTile("forecast", "device.forecast", decoration: "flat") {
            state "default",        label: 'updating...',        icon: "st.unknown.unknown.unknown"
            state "chanceflurries", label: 'Chance of Flurries', icon: "st.Weather.weather6"
            state "chancerain",     label: 'Chance of Rain',     icon: "st.Weather.weather9"
            state "chancesleet",    label: 'Chance of Sleet',    icon: "st.Weather.weather6"
            state "chancesnow",     label: 'Chance of Snow',     icon: "st.Weather.weather6"
            state "chancetstorms",  label: 'Chance of TStorms',  icon: "st.Weather.weather9"
            state "clear",          label: 'Clear',              icon: "st.Weather.weather14"
            state "cloudy",         label: 'Cloudy',             icyon: "st.Weather.weather15"
            state "flurries",       label: 'Flurries',           icon: "st.Weather.weather6"
            state "fog",            label: 'Fog',                icon: "st.Weather.weather13"
            state "hazy",           label: 'Hazy',               icon: "st.Weather.weather13"
            state "mostlycloudy",   label: 'Mostly Cloudy',      icon: "st.Weather.weather15"
            state "mostlysunny",    label: 'Mostly Sunny',       icon: "st.Weather.weather11"
            state "partlycloudy",   label: 'Partly Cloudy',      icon: "st.Weather.weather11"
            state "partlysunny",    label: 'Partly Sunny',       icon: "st.Weather.weather11"
            state "sleet",          label: 'Sleet',              icon: "st.Weather.weather10"
            state "rain",           label: 'Rain',               icon: "st.Weather.weather10"
            state "snow",           label: 'Snow',               icon: "st.Weather.weather7"
            state "sunny",          label: 'Sunny',              icon: "st.Weather.weather14"
            state "tstorms",        label: 'Thunder Storms',     icon: "st.Weather.weather10"
        }
        standardTile("conditions", "device.forecast", decoration: "flat",width: 2, height: 1) {
            state "default", label: 'Fcast\n${currentValue}'        
        }
        valueTile("wind_speed", "device.wind_speed", inactiveLabel: false, decoration: "flat",width: 1, height: 1) {
            state "wind_speed", label: 'W.Speed\n${currentValue}', backgroundColors: [
                // Values and colors based on the Beaufort Scale
                // http://en.wikipedia.org/wiki/Beaufort_scale#Modern_scale
                [value: 0,  color: "#ffffff"],
                [value: 1,  color: "#ccffff"],
                [value: 4,  color: "#99ffcc"],
                [value: 8,  color: "#99ff99"],
                [value: 13, color: "#99ff66"],
                [value: 18, color: "#99ff00"],
                [value: 25, color: "#ccff00"],
                [value: 31, color: "#ffff00"],
                [value: 39, color: "#ffcc00"],
                [value: 47, color: "#ff9900"],
                [value: 55, color: "#ff6600"],
                [value: 64, color: "#ff3300"],
                [value: 74, color: "#ff0000"]
            ]
        }
        valueTile("wind_direction", "device.wind_direction", inactiveLabel: false, width: 1, height: 1,decoration: "flat") {
            state "wind_direction", label: '${currentValue}', unit: "Direction"
        }

        // Third Row
        valueTile("uv_index", "device.uv_index", decoration: "flat") {
            state "uv_index", label: '${currentValue}UV', unit: "UV Index"
        }
        standardTile("water", "device.water", decoration: "flat",inactiveLabel: false) {
            state "default", label: 'updating...', icon: "st.unknown.unknown.unknown"
            state "true",    label:'Wet',          icon: "st.alarm.water.wet",        backgroundColor:"#ff9999"
            state "false",   label:'Dry',          icon: "st.alarm.water.dry",        backgroundColor:"#99ff99"
        }
        valueTile("precipitation", "device.precipitation", inactiveLabel: false, width: 1, height: 1,decoration: "flat") {
            state "precipitation", label: 'Precip\n ${currentValue}'
        }
        valueTile("location", "device.location", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
            state "location", label: 'Location\n${currentValue}', unit: "ZipCode"
        }

        // Fourth Row
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat") {
            state "default", action:"polling.poll", icon:"st.secondary.refresh"
        }

        // This tile will be the tile that is displayed on the Hub page.
        main "temperature"

        // These tiles will be displayed when clicked on the device, in the order listed here.
        details(["temperature", "humidity", "feels_like", "forecast","conditions", "wind_speed", "wind_direction", "uv_index", "water", "precipitation", "location", "refresh"])
    }
}

// As an object that can be polled, the poll() function will be called on each polling cycle.
def poll() {
    def weather

    // If there is a zipcode defined, use it, otherwise SmartThings will determine your current location from the Hub.
    if (settings.zipcode) {
        log.debug "ZipCode: ${settings.zipcode}"
        weather = getWeatherFeature( "conditions", settings.zipcode )
    } else {
        log.debug "ZipCode: (automatic)"
        weather = getWeatherFeature( "conditions" )
    }

    // Check if the variable is populated, otherwise return.
    if (!weather) {
        log.debug( "Something went wrong, no data found." )
        return false
    }

    // UV Index
    log.debug( "UV Index: ${weather.current_observation.UV}" )
    sendEvent( name: 'uv_index', value: weather.current_observation.UV.toString() )

    // Forecast
    log.debug( "Forecast: ${weather.current_observation.icon}" )
    sendEvent( name: "forecast", value: weather.current_observation.icon )

    log.debug( "Wind Direction: ${weather.current_observation.wind_dir}" )
    sendEvent( name: "wind_direction", value: weather.current_observation.wind_dir )
    // Set the tiles

    
    log.debug( "Relative Humidity: ${weather.current_observation.relative_humidity.tokenize('%')[0].toInteger()}" )
    sendEvent( name: 'humidity', value: weather.current_observation.relative_humidity.tokenize('%')[0].toInteger(),
        unit: "%")

    def scale = getTemperatureScale()
    if (scale == 'C') { 
     
        def currentTempFormat = String.format('%2.1f', fToC(weather.current_observation.temp_f.toFloat()).round(1))
        def feelTempFormat =  String.format('%2.1f', fToC(weather.current_observation.feelslike_f.toFloat()).round(1))
        log.debug( "Temperature: ${currentTempFormat}")
        log.debug( "Feels Like: ${feelTempFormat}")
        sendEvent( name: 'feels_like', value: feelTempFormat, unit: "C")
        sendEvent( name: 'temperature', value: feelTempFormat, unit: "C")
        float windSpeed=milesToKm(weather.current_observation.wind_mph.toFloat())
        def speedFormat= String.format('%2.1f', windSpeed.round(1))
        log.debug( "Wind Speed: ${speedFormat} kmh")
        sendEvent( name: "wind_speed", value: speedFormat, unit: "kmh" )
    }
    else {

        log.debug( "Feels Like: ${weather.current_observation.feelslike_f}" )
        log.debug( "Temperature: ${weather.current_observation.temp_f}")
        sendEvent( name: 'feels_like', value: weather.current_observation.feelslike_f,unit: "F" )
        sendEvent( name: 'temperature', value: weather.current_observation.feelslike_f, unit: "F")
        // Wind
        log.debug( "Wind Speed: ${weather.current_observation.wind_mph} mph")
        sendEvent( name: "wind_speed", value: weather.current_observation.wind_mph.toInteger(), unit: "mph" )
    }
    
    
    log.debug( "Location: ${weather.current_observation.display_location.zip.toString()}" )
    sendEvent( name: 'location', value: weather.current_observation.display_location.zip.toString())
    
    float precip 
    if (scale == 'C') { 
     
        precip = inchToMm(weather.current_observation.precip_1hr_in.toFloat())
        log.debug( "precipitation: ${precip.round(1).toString()}")
        sendEvent( name: 'precipitation', value: precip.round(1).toString(), unit: "mm" )
    }
    else {
        precip =weather.current_observation.precip_1hr_in.toFloat()
        sendEvent( name: 'precipitation', value: weather.current_observation.precip_1hr_in, unit: "inches" )
    }
    
    if (precip > 0) {
            
        if (currentTemp < 0) {
        
            sendEvent( name: 'snow', value: "true" )
        }
        else {
            sendEvent( name: 'water', value: "true" )
                
        }
        
     } else {
        log.debug( "precipitation: None" )
        sendEvent( name: 'water', value: "false" )
        sendEvent( name: 'snow', value: "false" )
    }    
}

def cToF(temp) {
    return temp * 1.8 + 32
}

def fToC(temp) {
    return (temp - 32) / 1.8
}

def milesToKm(distance) {
    return (distance * 1.609344)
}

def inchToMm(len) {
    return (len * 25.400)


}
