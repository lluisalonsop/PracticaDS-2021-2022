public class SearchByTagVisitor implements Visitor {
    private String tag;

    public SearchByTagVisitor(String tag) {
        super();
        this.tag = tag;
    }

    @Override

    public Node visitNode(Node p) {
        for (int i = 0; i < p.tags.size(); i++) {
            if (p.tags.get(i) == tag) {
                return p;
            }
        }
        return new Project("null");
    }

    public void visitTask(Task t) {
    }
}
