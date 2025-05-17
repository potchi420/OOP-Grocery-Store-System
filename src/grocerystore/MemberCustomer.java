package grocerystore;

public class MemberCustomer extends Customer
{
    private String tier;

    public MemberCustomer(String name, String tier)
    {
        super(name);
        this.tier = tier;
    }

    @Override
    public String getTier()
    {
        return tier;
    }

    public void setTier(String tier)
    {
        this.tier = tier;
    }
}
