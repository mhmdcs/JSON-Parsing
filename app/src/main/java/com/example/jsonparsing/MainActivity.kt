package com.example.jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

//https://tutorials.eu/json-parsing-and-how-to-use-gson-in-android/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create instance of users list using the User model data class
        //val usersList: ArrayList<User> = ArrayList() this was only used for the manual method

        try {

            //Parse JSON automatically through third-party library GSON
            val jsonString = getJSONFromAssets()!!
            val usersArray = Gson().fromJson(jsonString, UsersArray::class.java)

            //Parse JSON manually
             /*
            //Call getJSONFromAssets method that returns the JSON object in string format from the assets folder
            //and use JSONObject method to convert the string into an actual JSON Object
            val jsonUsersObject = JSONObject(getJSONFromAssets()!!)

            // fetch JSONArray named "users" by using getJSONArray method
            val jsonUsersArray = jsonUsersObject.getJSONArray("users")

            // Get the users data(id, name, email and so on) using for loop that runs through all the users JSON Array indices
            for (i in 0 until jsonUsersArray.length()) {

                // Create a JSON Object for the current user index
                val user = jsonUsersArray.getJSONObject(i)

                // Store the JSON data in variables
                val id = user.getInt("id")
                val name = user.getString("name")
                val email = user.getString("email")
                val gender = user.getString("gender")
                val weight = user.getDouble("weight")
                val height = user.getInt("height")

                // Create a JSON Object for the current user phone numbers, which is inside a "phones" JSON Object
                val phoneJsonObject = user.getJSONObject("phone")
                // Store the JSON mobile phone data in a variable
                val mobile = phoneJsonObject.getString("mobile")
                // Store the JSON office phone data in a variable
                val office = phoneJsonObject.getString("office")

                val phone = Phone(mobile, office)

                // Add all the stored variables to a User object from the model data class, and add that object to the array list.
                val userData = User(id, name, email, gender, weight, height, phone)

                // add the details in the array list
                usersList.add(userData)

            }*/



            // Initialize the Adapter class and pass the context and user list in the parameters.
            val userRecyclerAdapter = UserRecyclerAdapter(this, usersArray.users)

            // Set the LayoutManager that this RecyclerView will use.
            users_list_recyclerview.layoutManager = LinearLayoutManager(this)

            // Set the adapter instance to the recyclerview to inflate the items.
            users_list_recyclerview.adapter = userRecyclerAdapter

        } catch (e: JSONException) {
            // Print to the stack trace  if an exception occurs.
            e.printStackTrace()
        }

    }

    /**
     * Method to load the JSON from the assets folder and return the object as a string
     */
    private fun getJSONFromAssets(): String? {

        val jsonObjectString: String?
        val charset = Charsets.UTF_8

        //Stream: In laymen terms, stream is data, the most generic stream is binary representation of data.
        //Input Stream: If you are reading data from a file or any other source, the stream that is used is input stream. In a simpler terms, input stream acts as "a channel to read data".
        //Output Stream: If you want to read and process data from a source (file, etc) you first need to save/store the data, storing data is output stream.
        //here we'll deal with an Input Stream to load/read data from the Users.json file.
        try {
            // Create InputStream object that opens the Users.json file from the assets folder
            val usersJSONFile = assets.open("Users.json")

            // Store the estimate of the number of bytes that can be read (or skipped over) from this input stream (i.e. the Users.json file), and initialize a Bytes Array of that size named "buffer"
            val size = usersJSONFile.available()
            val buffer = ByteArray(size)

            usersJSONFile.read(buffer)
            usersJSONFile.close()
            jsonObjectString = String(buffer, charset)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return jsonObjectString
    }
}
