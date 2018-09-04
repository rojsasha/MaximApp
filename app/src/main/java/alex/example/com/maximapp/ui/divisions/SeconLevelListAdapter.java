package alex.example.com.maximapp.ui.divisions;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alex.example.com.maximapp.R;
import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.data.entity.Employee;

import static android.view.View.GONE;

public class SeconLevelListAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "SeconLevelListAdapter";
    private Context mContext;
    private List<Department> mDepartmentList;
    private List<Department> childList = new ArrayList<>();

    SeconLevelListAdapter(Context context, List<Department> list) {
        mContext = context;
        mDepartmentList = list;
    }

    @Override
    public int getGroupCount() {
        return mDepartmentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (mDepartmentList.get(i).getDepartments() != null)
            return 1;

        if (mDepartmentList.get(i).getEmployees() != null)
            return mDepartmentList.get(i).getEmployees().size();

        return 0;
    }


    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i1;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderParent holderParent;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_parent, viewGroup, false);
            holderParent = new ViewHolderParent(view);
            view.setTag(holderParent);
        } else {
            holderParent = (ViewHolderParent) view.getTag();
        }

        holderParent.tvTitle.setText(mDepartmentList.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    if (mDepartmentList.get(i).getEmployees() != null) {
            view = inflater.inflate(R.layout.item_employee, null);
            ViewHolderChild holderChild = new ViewHolderChild(view);
            Employee employee = mDepartmentList.get(i).getEmployees().get(i1);
            holderChild.tvTitle.setText(employee.getTitle());
            holderChild.tvName.setText(employee.getName());
            holderChild.tvEmail.setText(employee.getEmail());
            holderChild.tvPhone.setText(employee.getPhone());

        } else if (mDepartmentList.get(i).getDepartments() != null) {
            final SecondLevelExpadedListView secondLevelELV = new SecondLevelExpadedListView(mContext);
            secondLevelELV.setAdapter(new SeconLevelListAdapter(mContext, mDepartmentList.get(i).getDepartments()));

            Log.d("test", "getChildView: " + mDepartmentList.get(i).getDepartments().size());


            secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (groupPosition != previousGroup)
                        secondLevelELV.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                }
            });

            return secondLevelELV;
        }

        return view;
    }

    class ViewHolderChild {
        TextView tvTitle, tvName, tvEmail, tvPhone;

        ViewHolderChild(View view) {
            tvTitle = view.findViewById(R.id.tvTitle);
            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvPhone = view.findViewById(R.id.tvPhone);
        }
    }

    class ViewHolderParent {
        TextView tvTitle;

        ViewHolderParent(View view) {
            tvTitle = view.findViewById(R.id.tvParent);

        }
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
