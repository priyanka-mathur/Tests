import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class TopologicalSort2 {

    static class Project {
        static enum States {BLANK, PARTIAL, COMPLETE};
        String name;
        ArrayList<Project> children = new ArrayList<Project>();
        HashMap<String, Project> childrenMap = new HashMap<String, Project>();
        int dependencies = 0;
        States state = States.BLANK;

        Project(String n) {
            name = n;
        }

        void addNeighbour(Project node) {
            if(!childrenMap.containsKey(node.getName())) {
                children.add(node);
                childrenMap.put(node.getName(), node);
                node.incrementDependencies();
            }
        }

        void setState(States s) {
            state = s;
        }

        States getState() {
            return state;
        }

        String getName() {
            return name;
        }

        ArrayList<Project> getChildren() {
            return children;
        }

        int getNumberDependencies() {
            return dependencies;
        }

        void incrementDependencies() {
            dependencies++;
        }

        void decrementDependencies() {
            dependencies--;
        }

    }

    static class Graph {
        ArrayList<Project> nodes = new ArrayList<Project>();
        HashMap<String, Project> map = new HashMap<String, Project>();

        Project getOrCreateNode(String name) {
            if(!map.containsKey(name)) {
                Project p = new Project(name);
                nodes.add(p);
                map.put(name, p);
            }
            return map.get(name);
        }

        void addEdge(String start, String end) {
            Project startNode = getOrCreateNode(start);
            Project endNode = getOrCreateNode(end);
            startNode.addNeighbour(endNode);
        }

        ArrayList<Project> getNodes() {
            return nodes;
        }

    }

    static Stack<Project> findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }



    static Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for(String project: projects) {
            graph.getOrCreateNode(project);
        }
        for(String[] dependency : dependencies) {
            String first = dependency[0];
            String last = dependency[1];
            graph.addEdge(first, last);
        }
        return graph;
    }

    static Stack<Project> orderProjects(ArrayList<Project> projects) {
        Stack<Project> orderStack = new Stack<Project>();

        for(Project p: projects) {
            if(p.getState() == Project.States.BLANK) {
                if (!doDFS(p, orderStack)){
                    return null;
                }
            }
        }

        return orderStack;
    }

    static boolean doDFS(Project project, Stack<Project> orderStack) {
        if(project.getState() == Project.States.PARTIAL) {
            return false; //cycle
        }

        if(project.getState() == Project.States.BLANK) {
            project.setState(Project.States.PARTIAL);
            ArrayList<Project> children = project.getChildren();
            for(Project child : children) {
                if(!doDFS(child, orderStack)) {
                    return false;
                }
            }
            project.setState(Project.States.COMPLETE);
            orderStack.push(project);
        }
        return true;
    }

    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[][] dependencies = {
                {"a", "e"},
                {"b", "a"},
                {"b", "e"},
                {"b", "h"},
                {"c", "a"},
                {"d", "g"},
                {"f", "c"},
                {"f", "b"}
        };

        Stack<Project> order = findBuildOrder(projects, dependencies);
        while (!order.empty()){
            System.out.println(order.pop().getName());
        }
    }

}
