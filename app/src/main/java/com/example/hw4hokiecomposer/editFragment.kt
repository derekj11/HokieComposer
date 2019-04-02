package com.example.hw4hokiecomposer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import androidx.navigation.findNavController



/**
 * A simple [Fragment] subclass.
 *
 */
class editFragment : Fragment() {

    private lateinit var playButton: Button
    private lateinit var seek1: SeekBar
    private lateinit var seek2: SeekBar
    private lateinit var seek3: SeekBar

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

        val song: Spinner = view.findViewById(R.id.musicSpinner)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.songs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            song.adapter = adapter
        }

        playButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_editFragment_to_playFragment)
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

        seek1.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            var position1 = 0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position1 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

        seek2.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            var position2 = 0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position2 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

        seek3.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            var position3 = 0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                position3 = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        return view
    }

}
