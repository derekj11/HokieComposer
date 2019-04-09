package com.example.hw4hokiecomposer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.findNavController


/**
 * A simple [Fragment] subclass.
 *
 */
class EditFragment : Fragment() {

    private lateinit var playButton: Button
    private lateinit var seek1: SeekBar
    private lateinit var seek2: SeekBar
    private lateinit var seek3: SeekBar

    var position1 = 0
    var position2 = 0
    var position3 = 0

    private var selectedSong: String? = null
    private var effect1: String? = null
    private var effect2: String? = null
    private var effect3: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        playButton = view.findViewById(R.id.playButton)
        seek1 = view.findViewById(R.id.effect1seek)
        seek2 = view.findViewById(R.id.effect2seek)
        seek3 = view.findViewById(R.id.effect3seek)

        val songs: Spinner = view.findViewById(R.id.musicSpinner)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.songs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            songs.adapter = adapter
        }

        songs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSong = parent?.getItemAtPosition(position).toString()
            }

        }

        playButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_editFragment_to_playFragment,
                bundleOf("song" to selectedSong,
                    "effect1" to effect1,
                    "position1" to position1,
                    "effect2" to effect2,
                    "position2" to position2,
                    "effect3" to effect3,
                    "position3" to position3))
        }

        val choice1: Spinner = view.findViewById(R.id.effect1)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.effects,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            choice1.adapter = adapter
        }

        choice1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                effect1 = parent?.getItemAtPosition(position).toString()
            }

        }

        seek1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position1 = progress / 2
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

        })

        val choice2: Spinner  = view.findViewById(R.id.effect2)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.effects,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            choice2.adapter = adapter
        }

        choice2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                effect2 = parent?.getItemAtPosition(position).toString()
            }

        }

        seek2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position2 = progress / 2
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

        })

        val choice3: Spinner = view.findViewById(R.id.effect3)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.effects,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            choice3.adapter = adapter
        }

        choice3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing selected
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                effect3 = parent?.getItemAtPosition(position).toString()
            }

        }

        seek3.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position3 = progress / 2
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing
            }

        })

        return view
    }

}
