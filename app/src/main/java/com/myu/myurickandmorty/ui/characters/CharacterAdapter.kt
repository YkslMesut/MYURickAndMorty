package com.myu.myurickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.myu.myurickandmorty.data.entities.Character
import com.myu.myurickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter(private val listener : CharacterItemListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter ( characterId : Int)
    }

    private val items = ArrayList<Character>()

    fun setItems(items : ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding : ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding,listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(items[position])

    class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding , private val listener: CharacterItemListener) :
            RecyclerView.ViewHolder(itemCharacterBinding.root) ,
                View.OnClickListener {
                    private lateinit var character : Character

                    init {
                        itemCharacterBinding.root.setOnClickListener(this)
                    }
        fun bind(item : Character) {
            this.character = item
            itemCharacterBinding.name.text = item.name
            itemCharacterBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
            Glide.with(itemCharacterBinding.root)
                .load(item.image)
                .transform(CircleCrop())
                .into(itemCharacterBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onClickedCharacter(character.id)
        }
                }

}