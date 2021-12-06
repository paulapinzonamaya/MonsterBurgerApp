package com.example.monsterburgerapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.model.Comentario

class ComentarioAdapter (val comentarioList:List<Comentario>): RecyclerView.Adapter<ComentarioAdapter.ComentarioHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioHolder {

        var layoutInflater= LayoutInflater.from(parent.context)

        return ComentarioHolder(layoutInflater.inflate(R.layout.item_comments, parent, false))



    }

    override fun onBindViewHolder(holder: ComentarioHolder, position: Int) {
        holder.render(comentarioList[position])
    }

    override fun getItemCount(): Int {
        return comentarioList.size
    }



    class ComentarioHolder(val view: View): RecyclerView.ViewHolder(view){

        fun render(comentario: Comentario){
            var tvItemCommentUser = view.findViewById<TextView>(R.id.tvItemCommentsUser)
            var tvItemCommentScore = view.findViewById<TextView>(R.id.tvItemCommentsScore)
            var tvItemCommentComment = view.findViewById<TextView>(R.id.tvItemCommentsComment)


            tvItemCommentUser.text = comentario.nombreUsuario
            tvItemCommentScore.text = comentario.puntaje.toString() + "/5"
            tvItemCommentComment.text = comentario.texto

        }


    }

}
