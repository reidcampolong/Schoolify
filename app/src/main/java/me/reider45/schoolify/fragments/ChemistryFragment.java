package me.reider45.schoolify.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import me.reider45.schoolify.R;

/**
 * Created by Reid on 3/14/year.
 */
public class ChemistryFragment extends Fragment {

    final String[] atomicMasses = { "1.0079", "4.0026", "6.941", "9.0122", "10.811", "12.0107", "14.0067", "15.9994", "18.9984", "20.1797", "22.9897", "24.305", "26.9815", "28.0855", "30.9738", "32.065", "35.453", "39.0983", "39.948", "40.078", "44.9559", "47.867", "50.9415", "51.9961", "54.938", "55.845", "58.6934", "58.9332", "63.546", "65.39", "69.723", "72.64", "74.9216", "78.96", "79.904", "83.8", "85.4678", "87.62", "88.9059", "91.224", "92.9064", "95.94", "98", "101.07", "102.9055", "106.42", "107.8682", "112.411", "114.818", "118.71", "121.76", "126.9045", "127.6", "131.293", "132.9055", "137.327", "138.9055", "140.116", "140.9077", "144.24", "145", "150.36", "151.964", "157.25", "158.9253", "162.5", "164.9303", "167.259", "168.9342", "173.04", "174.967", "178.49", "180.9479", "183.84", "186.207", "190.23", "192.217", "195.078", "196.9665", "200.59", "204.3833", "207.2", "208.9804", "209", "210", "222", "223", "226", "227", "231.0359", "232.0381", "237", "238.0289", "243", "244", "247", "247", "251", "252", "257", "258", "259", "261", "262", "262", "264", "266", "268", "272", "277", "?", "?", "?", "?", "?", "?", "?", "?" };
    final String[] atomicSymbols = { "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "K", "Ar", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Ni", "Co", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "I", "Te", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Pa", "Th", "Np", "U", "Am", "Pu", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Rf", "Lr", "Db", "Bh", "Sg", "Mt", "Rg", "Hs", "Ds", "Uub", "Uut", "Uuq", "Uup", "Uuh", "Uus", "Uuo" };
    final String[] atomicNumbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "19", "18", "20", "21", "22", "23", "24", "25", "26", "28", "27", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "53", "52", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "90", "93", "92", "95", "94", "96", "97", "98", "99", "100", "101", "102", "104", "103", "105", "107", "106", "109", "111", "108", "110", "112", "113", "114", "115", "116", "117", "118" };
    final String[] atomicNames = { "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Potassium", "Argon", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Nickel", "Cobalt", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Iodine", "Tellurium", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Protactinium", "Thorium", "Neptunium", "Uranium", "Americium", "Plutonium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Rutherfordium", "Lawrencium", "Dubnium", "Bohrium", "Seaborgium", "Meitnerium", "Roentgenium", "Hassium", "Darmstadtium", "Ununbium", "Ununtrium", "Ununquadium", "Ununpentium", "Ununhexium", "Ununseptium", "Ununoctium" };

    Button btnSearch;
    Spinner dropdown;
    TextView lblElement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chemistry, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Register fields
        btnSearch = (Button) getActivity().findViewById(R.id.btnSearch);
        dropdown = (Spinner) getActivity().findViewById(R.id.spnElement);
        lblElement = (TextView) getActivity().findViewById(R.id.lblElement);

        // Register spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, atomicNames);
        dropdown.setAdapter(adapter);

        // Search for information
        findInformation();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findInformation();
            }
        });

    }

    public void findInformation() {
        // Get selected item
        String selected = dropdown.getSelectedItem().toString();
        // Find its position
        int position = Arrays.asList(atomicNames).indexOf(selected);

        // Get all data relevant to that position
        String name = selected;
        String symbol = atomicSymbols[position];
        String atomicMass = atomicMasses[position];
        String number = atomicNumbers[position];
        String builder = symbol + "\n" + name + "\n" + "Atomic Number: " + number + "\n" + "Atomic Mass: " + atomicMass;

        // Set the label
        lblElement.setText(builder);
    }

}
