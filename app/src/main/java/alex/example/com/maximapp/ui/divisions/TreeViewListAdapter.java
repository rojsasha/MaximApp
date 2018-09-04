package alex.example.com.maximapp.ui.divisions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import alex.example.com.maximapp.R;
import alex.example.com.maximapp.data.entity.Department;
import alex.example.com.maximapp.data.entity.Employee;

public class TreeViewListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Department> mDepartmentList;
    private int countChild = 0;

    TreeViewListAdapter(Context context, List<Department> list) {
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_parent, null);
        TextView text = view.findViewById(R.id.tvParent);
        text.setText(mDepartmentList.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (mDepartmentList.get(i).getEmployees() != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_employee, null);
            ViewHolderChild holderChild = new ViewHolderChild(view);
            Employee employee = mDepartmentList.get(i).getEmployees().get(i1);
            holderChild.tvTitle.setText(employee.getTitle());
            holderChild.tvName.setText(employee.getName());
            holderChild.tvEmail.setText(employee.getEmail());
            holderChild.tvPhone.setText(employee.getPhone());

        } else if (mDepartmentList.get(i).getDepartments() != null) {
            final SecondLevelExpadedListView secondLevelELV = new SecondLevelExpadedListView(mContext);
            secondLevelELV.setAdapter(new TreeViewListAdapter(mContext, mDepartmentList.get(i).getDepartments()));
            if (countChild != 0) {
                secondLevelELV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                Toast.makeText(mContext, "2222222", Toast.LENGTH_SHORT).show();
            }
            countChild = mDepartmentList.get(i).getDepartments().size();

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

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
