package com.example.appgrupo13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.appgrupo13.room_database.ToDoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmento: View = inflater.inflate(R.layout.fragment_to_do, container, false)
/*
        val detail1: Button = fragmento.findViewById(R.id.btn_detail_1)
        val detail2: Button = fragmento.findViewById(R.id.btn_detail_2)
        val detail3: Button = fragmento.findViewById(R.id.btn_detail_3)
        detail1.setOnClickListener { it ->
            val datos = Bundle()
            datos.putString("tarea", "Ir al supermercado")
            datos.putString("hora", "10:00")
            datos.putString("lugar", "Exito")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }

        detail2.setOnClickListener { it ->
            val datos = Bundle()
            datos.putString("tarea", "Llevar carro a mantenimiento")
            datos.putString("hora", "12:00")
            datos.putString("lugar", "Taller")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }

*/

        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerTodoList: RecyclerView = view.findViewById(R.id.recyclerTodoList)
        var datos: ArrayList<Task> = ArrayList()
        val dbFirebase = FirebaseFirestore.getInstance()
        dbFirebase.collection("ToDo")
//            .whereEqualTo("title", "Estudiar")
            .orderBy("time")
            .get()
            .addOnSuccessListener {
                for (todo in it) {
                    var id = todo.id
                    datos.add(
                        Task(
                            id.toInt(), todo.get("title") as String,
                            todo.get("time") as String, todo.get("place") as String
                        )
                    )
                }
                var taskAdapter = TaskAdapter(datos) {
                    val datos = Bundle()
                    datos.putInt("id", it.id)
                    Navigation.findNavController(view).navigate(R.id.nav_detail, datos)
                }
                recyclerTodoList.setHasFixedSize(true)
                recyclerTodoList.adapter = taskAdapter
                recyclerTodoList.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

/*
        val room: ToDoDatabase = Room.databaseBuilder(context?.applicationContext!!,
            ToDoDatabase::class.java, "ToDoDatabaase").build()
        var todoDao = room.todoDao()
        runBlocking {
            launch {
                var result = todoDao.getAllTasks()
                for (todo in result){
                    datos.add(Task(todo.id, todo.title, todo.time, todo.place))
                }
            }
        }
*/

/*
        var taskAdapter = TaskAdapter(datos){
            val datos = Bundle()
            datos.putInt("id", it.id)
*/
/*
            datos.putString("tarea", it.task)
            datos.putString("hora", it.time)
            datos.putString("lugar", it.place)
*/
//            Navigation.findNavController(view).navigate(R.id.nav_detail, datos)

/*
        recyclerTodoList.setHasFixedSize(true)
        recyclerTodoList.adapter = taskAdapter
        recyclerTodoList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
*/
/*
        datos.add(Task("Ir al supermercado", "10:00", "Exito"))
        datos.add(Task("Llevar carro a mantenimiento", "12:00", "Taller"))
        datos.add(Task("Ir a lavanderia", "15:00", "Lavaseco"))
*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToDoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}