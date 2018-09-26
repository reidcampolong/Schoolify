package me.reider45.schoolify.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import me.reider45.schoolify.R;

/**
 * Created by Reid.
 */
public class MathFragment extends Fragment {

    EditText txtA, txtB, txtC, txtX1, txtX2, txtY1, txtY2;
    TextView lblQuadratic, lblDistance;
    Button btnSolveQuadratic, btnSolveDistance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_math, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Register all frields
        txtA = (EditText) getActivity().findViewById(R.id.txtA);
        txtB = (EditText) getActivity().findViewById(R.id.txtB);
        txtC = (EditText) getActivity().findViewById(R.id.txtC);

        txtX1 = (EditText) getActivity().findViewById(R.id.txtX1);
        txtX2 = (EditText) getActivity().findViewById(R.id.txtX2);

        txtY1 = (EditText) getActivity().findViewById(R.id.txtY1);
        txtY2 = (EditText) getActivity().findViewById(R.id.txtY2);

        lblQuadratic = (TextView) getActivity().findViewById(R.id.lblQuadratic);
        lblDistance = (TextView) getActivity().findViewById(R.id.lblDistance);

        btnSolveQuadratic = (Button) getActivity().findViewById(R.id.btnQuadratic);
        btnSolveDistance = (Button) getActivity().findViewById(R.id.btnDistance);

        // Button listeners
        btnSolveQuadratic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solveQuadratic();
            }
        });

        btnSolveDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solveDistance();
            }
        });

    }

    public void solveQuadratic() {
        try {
            int a = Integer.parseInt(txtA.getText().toString());
            int b = Integer.parseInt(txtB.getText().toString());
            int c = Integer.parseInt(txtC.getText().toString());

            double x1, x2;

            // What's inside of the square root
            double discriminant = Math.sqrt((b * b - (4 * a * c)));
            if (discriminant > 0) {
                // Positive
                // Solve
                x1 = (((-1 * b) + (discriminant)) / (2 * a));
                x2 = (((-1 * b) - (discriminant)) / (2 * a));
                lblQuadratic.setText("X Can Equal " + x1 + " or " + x2);
            } else {
                // No Real Solutions
                // Can't solve
               lblQuadratic.setText("No Real Solutions");
            }
        } catch(Exception e) {
            lblQuadratic.setText("No Real Solutions");
        }

    }

    public void solveDistance() {
        try {
            double x1 = Double.parseDouble(txtX1.getText().toString());
            double x2 = Double.parseDouble(txtX2.getText().toString());
            double y1 = Double.parseDouble(txtY1.getText().toString());
            double y2 = Double.parseDouble(txtY2.getText().toString());

            // What's inside of the square root
            double discriminant = (Math.pow((x2 - x1), 2) + Math.pow((y2-y1), 2));

            // Try to solve
            double answer = Math.sqrt(discriminant);

            // Must be a real number
            if(Double.isNaN(answer)) {
                lblDistance.setText("Invalid Points!");
            } else {
                lblDistance.setText("The distance between ("+x1+","+y1+") and ("+x2+","+y2+") is " + answer);
            }

        } catch(Exception e) {
            lblDistance.setText("Invalid Points!");
        }
    }


}
