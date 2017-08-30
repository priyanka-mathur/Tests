import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TopologicalSort {

    static class Project {
        String name;
        ArrayList<Project> children = new ArrayList<Project>();
        HashMap<String, Project> childrenMap = new HashMap<String, Project>();
        int dependencies = 0;

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

    static Project[] findBuildOrder(String[] projects, String[][] dependencies) {
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

    static Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()];

        int endOfList = addNonDependent(order, projects, 0);
        int toBeProcessed = 0;

        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];

            if(current == null) {
                return null;
            }

            ArrayList<Project> children = current.getChildren();
            for(Project child: children) {
                child.decrementDependencies();
            }

            endOfList = addNonDependent(order, children, endOfList);

            toBeProcessed++;
        }

        return order;
    }

    static int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for(Project p: projects) {
            if(p.getNumberDependencies() == 0) {
                order[offset] = p;
                offset++;
            }
        }
        return offset;
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

        Project order[] = findBuildOrder(projects, dependencies);
        for(int i =0; i<order.length; i++) {
            System.out.println(order[i].name);
        }
    }

}
