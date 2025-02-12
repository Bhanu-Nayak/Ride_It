export function Card({ children, className }) {
  return (
    <div
      className={
        `p-6 rounded-2xl bg-gray-900 text-gray-900 ${className}` //bg-gray-950
      }
    >
      {children}
    </div>
  );
}

export function CardContent({ children }) {
  return <div className="mt-4">{children}</div>;
}

export function CardHeader({ children }) {
  return <div className="text-center mb-4">{children}</div>;
}

export function CardTitle({ children }) {
  return <h2 className="text-xl font-bold">{children}</h2>;
}
